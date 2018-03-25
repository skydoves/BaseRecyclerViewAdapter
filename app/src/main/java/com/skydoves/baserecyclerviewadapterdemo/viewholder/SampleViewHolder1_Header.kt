package com.skydoves.baserecyclerviewadapterdemo.viewholder

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_sample1_header.view.*

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class SampleViewHolder1_Header(view: View): BaseViewHolder(view) {

    override fun bindData(data: Any) {
        if(data is String) {
            itemView.sample1_header.text = data
        }
    }

    override fun onClick(v: View?) {
    }

    override fun onLongClick(v: View?) = false
}
