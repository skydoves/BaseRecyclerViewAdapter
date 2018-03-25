package com.skydoves.baserecyclerviewadapterdemo.adapter

import android.view.View

import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.baserecyclerviewadapterdemo.R
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class SampleAdapter0(private val delegate: SampleViewHolder.Delegate) : BaseAdapter() {

    private val section_item = 0

    init {
        addSection(ArrayList<SampleItem>())
    }

    fun addItems(sampleItems: List<SampleItem>) {
        sections[section_item].addAll(sampleItems)
        notifyDataSetChanged()
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_sample
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return SampleViewHolder(view, delegate)
    }
}
