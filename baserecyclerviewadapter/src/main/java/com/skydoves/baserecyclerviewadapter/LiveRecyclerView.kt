/*
 * Copyright (C) 2018 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.baserecyclerviewadapter

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused", "MemberVisibilityCanBePrivate")
class LiveRecyclerView : RecyclerView {

    private var loadPublisher = MutableLiveData<Int>()

    private var pageCount: Int = 1
    var totalPage: Int = -1

    private var pagingThreshold = 5

    private var isLoading = false

    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            recyclerView.layoutManager?.let {
                val visibleItemCount = it.childCount
                val totalItemCount = it.itemCount
                val firstVisibleItemPosition = when (it) {
                    is LinearLayoutManager -> it.findLastVisibleItemPosition()
                    is GridLayoutManager -> it.findLastVisibleItemPosition()
                    else -> return
                }

                if (totalPage < pageCount || isLoading) return

                if ((visibleItemCount + firstVisibleItemPosition + pagingThreshold) >= totalItemCount)
                    loadPublisher.value = pageCount
            }
        }
    }

    constructor(context: Context) : super(context) {
        this.addOnScrollListener(onScrollListener)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.addOnScrollListener(onScrollListener)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        this.addOnScrollListener(onScrollListener)
    }

    fun loadMore(): LiveData<Int> {
        return this.loadPublisher
    }

    fun countPage() {
        this.pageCount++
    }

    fun getPageCount(): Int {
        return this.pageCount
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun resetPage() {
        pageCount = 1
    }

    fun setPagingThreshold(threshold: Int) {
        this.pagingThreshold = threshold
    }
}
