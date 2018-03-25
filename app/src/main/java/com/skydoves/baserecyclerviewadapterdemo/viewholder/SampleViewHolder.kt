package com.skydoves.baserecyclerviewadapterdemo.viewholder

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import kotlinx.android.synthetic.main.item_sample.view.*
import org.jetbrains.anko.image

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

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
