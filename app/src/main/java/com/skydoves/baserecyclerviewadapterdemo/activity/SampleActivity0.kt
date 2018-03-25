package com.skydoves.baserecyclerviewadapterdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.skydoves.baserecyclerviewadapterdemo.MockSamples
import com.skydoves.baserecyclerviewadapterdemo.R
import com.skydoves.baserecyclerviewadapterdemo.adapter.SampleAdapter0
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder
import kotlinx.android.synthetic.main.activity_sample0.*
import org.jetbrains.anko.toast

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

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
