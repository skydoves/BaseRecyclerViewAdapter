package com.skydoves.baserecyclerviewadapterdemo.adapter

import android.content.res.Resources
import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.baserecyclerviewadapterdemo.R
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder1_Header

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

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
