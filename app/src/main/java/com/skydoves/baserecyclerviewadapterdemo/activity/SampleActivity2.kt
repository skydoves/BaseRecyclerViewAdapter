package com.skydoves.baserecyclerviewadapterdemo.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.baserecyclerviewadapterdemo.MockSamples
import com.skydoves.baserecyclerviewadapterdemo.R
import com.skydoves.baserecyclerviewadapterdemo.adapter.SampleAdapter0
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder
import kotlinx.android.synthetic.main.activity_sample2.*
import org.jetbrains.anko.toast

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class SampleActivity2 : AppCompatActivity(), SampleViewHolder.Delegate {

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
                isLoading = {false }
        )
        loadMore()
    }

    private fun loadMore() {
        adapter.addItems(MockSamples.mockSampleItemsRandom(this, paginator.currentPage * 10,10))
    }

    override fun onItemClick(sampleItem: SampleItem) {
        toast(sampleItem.name)
    }
}
