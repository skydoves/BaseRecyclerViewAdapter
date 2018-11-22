package com.skydoves.baserecyclerviewadapterdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.baserecyclerviewadapterdemo.MockSamples
import com.skydoves.baserecyclerviewadapterdemo.R
import com.skydoves.baserecyclerviewadapterdemo.adapter.SampleAdapter1
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder
import kotlinx.android.synthetic.main.activity_sample1.*
import org.jetbrains.anko.toast

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class SampleActivity1 : AppCompatActivity(), SampleViewHolder.Delegate {

    private val adapter by lazy { SampleAdapter1(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)

        sample1_recyclerView.adapter = adapter
        sample1_recyclerView.layoutManager = LinearLayoutManager(this)

        for(i in 0..5) {
            adapter.addItems(i, MockSamples.mockSampleItems(this, 3))
        }
    }

    override fun onItemClick(sampleItem: SampleItem) {
        toast(sampleItem.name)
    }
}
