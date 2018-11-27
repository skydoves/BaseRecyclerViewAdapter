# BaseRecyclerViewAdapter <br>
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Build Status](https://travis-ci.org/skydoves/BaseRecyclerViewAdapter.svg?branch=master)](https://travis-ci.org/skydoves/BaseRecyclerViewAdapter)
<br>
Adapter and ViewHolder that let you implement easily and a RecyclerView to be split into multi-sectioned.<br>
And lets you implement paging and endless-recyclerView easily.<br>

![demo0](https://user-images.githubusercontent.com/24237865/37874830-b05ad8ea-3071-11e8-906e-670f56d6912b.png)
![demo1](https://user-images.githubusercontent.com/24237865/37874865-16e6bb42-3072-11e8-9c6c-aa739cb05410.png)

## Including in your project
#### build.gradle
```gradle
dependencies {
    implementation "com.github.skydoves:baserecyclerviewadapter:0.1.2"
}
```

## Usage
1. Create a custom ViewHolder class extending `BaseViewHolder` by your custom layout.<br>
`bindData` method receives an item model what "Any" type in Kotlin or "object" type in Java.<br>
and you can implement `onClickItem` listener about the item or whatever.

```java
class SampleViewHolder(view: View, private val delegate: Delegate) : BaseViewHolder(view) {

    private lateinit var sampleItem: SampleItem

    interface Delegate {
        fun onItemClick(sampleItem: SampleItem)
    }

    override fun bindData(data: Any) {
        if(data is SampleItem) {
            sampleItem = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            sample0_avatar.image = sampleItem.image
            sample0_name.text = sampleItem.name
            sample0_content.text = sampleItem.content
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(this.sampleItem)
    }

    override fun onLongClick(v: View?) = false
}
```

2. Create a custom Adapter class extending `BaseAdapter`.

```java
class SampleAdapter0(private val delegate: SampleViewHolder.Delegate) : BaseAdapter() {

    private val section_item = 0

    init {
        addSection(ArrayList<SampleItem>())
    }

    fun addItems(sampleItems: List<SampleItem>) {
        addItemsOnSection(section_item, sampleItems)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_sample
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return SampleViewHolder(view, delegate)
    }
}
```

3. And use at Activities or Fragments.
```java
class SampleActivity0 : AppCompatActivity(), SampleViewHolder.Delegate {

    private val adapter by lazy { SampleAdapter0(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample0)

        sample0_recyclerView.adapter = adapter
        sample0_recyclerView.layoutManager = LinearLayoutManager(this)
        mockItems()
    }

    private fun mockItems() {
        adapter.addItems(MockSamples.mockSampleItems(this, 15))
    }

    override fun onItemClick(sampleItem: SampleItem) {
        toast(sampleItem.name)
    }
}
```

### Multi-Type Rows
If you want to implement multi-sections or rows on a RecyclerView, you should create more than two custom ViewHolders.<br>
And you can handle multi-layout like below.

```java
class SampleAdapter1(private val delegate: SampleViewHolder.Delegate): BaseAdapter() {

    init {
        for(i in 0..5) {
            addSection(ArrayList<Any>())
        }
    }

    fun addItems(section: Int, items: List<SampleItem>) {
        addItemOnSection(section, "Section$section")
        addItemsOnSection(section, items)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int {
        when(sectionRow.row()) {
            0 -> return R.layout.item_sample1_header
            else -> return R.layout.item_sample
        }
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        when(layout) {
            R.layout.item_sample1_header -> return SampleViewHolder1_Header(view)
            R.layout.item_sample -> return SampleViewHolder(view, delegate)
        }
        throw Resources.NotFoundException("not founded layout")
    }
}
```

### Multi-Type Sections
Or you can handle multi-layout by sections like below.<br>
```java
class GithubUserAdapter(val delegate_header: GithubUserHeaderViewHolder.Delegate,
                        val delegate: GithubUserViewHolder.Delegate) : BaseAdapter() {

    private val section_header = 0
    private val section_follower = 1

    init {
        addSection(ArrayList<GithubUser>())
        addSection(ArrayList<Follower>())
    }

    fun updateHeader(resource: Resource<GithubUser>) {
        resource.data?.let {
            sections[section_header].clear()
            sections[section_header].add(it)
            notifyDataSetChanged()
        }
    }

    fun addFollowList(followers: List<Follower>) {
        sections[section_follower].addAll(followers)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: BaseAdapter.SectionRow): Int {
        when (sectionRow.section()) {
            section_header -> return R.layout.layout_detail_header
            else -> return R.layout.item_github_user
        }
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        when (layout) {
            R.layout.layout_detail_header -> return GithubUserHeaderViewHolder(view, delegate_header)
            else -> return GithubUserViewHolder(view, delegate)
        }
    }
```

### RecyclerViewPaginator
RecyclerViewPaginator lets you implementation paging and endless-recyclerView easily.<br><br>
![demo2](https://user-images.githubusercontent.com/24237865/37875097-a49f5f90-3075-11e8-9ece-42ef11aebbb1.gif)

RecylcerViewPaginator performs invoke loadMore when recyclerView needs to load more items.<br>
And it would not be called when fetching from network or loading ended.<br>
This is an example of endless-recyclerView.<br>

```java
private val adapter by lazy { SampleAdapter0(this) }
private lateinit var paginator: RecyclerViewPaginator

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sample2)

    sample2_recyclerView.adapter = adapter
    sample2_recyclerView.layoutManager = LinearLayoutManager(this)
    paginator = RecyclerViewPaginator(
            recyclerView = sample2_recyclerView,
            onLast = { false },
            loadMore = { loadMore() },
            isLoading = { false }
    )
    loadMore()
}

private fun loadMore() {
    adapter.addItems(MockSamples.mockSampleItemsRandom(this, paginator.currentPage * 10, 10))
}
```

This is an [example](https://github.com/skydoves/GithubFollows/blob/master/app/src/main/java/com/skydoves/githubfollows/view/ui/main/MainActivity.kt) of RecyclerViewPaginator with ViewModel's network fetching. <br>
And you can reference more at this [repository](https://github.com/skydoves/GithubFollows).

```java
override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    main_recyclerView.adapter = adapter
    main_recyclerView.layoutManager = LinearLayoutManager(this)
    paginator = RecyclerViewPaginator(
            recyclerView = main_recyclerView,
            isLoading = { viewModel.fetchStatus.isOnLoading },
            loadMore = { loadMore(it) },
            onLast = { viewModel.fetchStatus.isOnLast }
    )

    initializeUI()
    observeViewModel()
}

private fun loadMore(page: Int) {
    viewModel.postPage(page)
}

private fun updateGithubUser(resource: Resource<GithubUser>) {
    when (resource.status) {
        Status.SUCCESS -> adapter.updateHeader(resource)
        Status.ERROR -> toast(resource.message.toString())
        Status.LOADING -> {
        }
    }
}
```

# License
```xml
Copyright 2018 skydoves

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
