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

package com.skydoves.baserecyclerviewadapterdemo.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.baserecyclerviewadapterdemo.R
import com.skydoves.baserecyclerviewadapterdemo.databinding.ItemSample1HeaderBinding
import com.skydoves.baserecyclerviewadapterdemo.databinding.ItemSampleBinding
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder1Header

class SampleAdapter1(private val delegate: SampleViewHolder.Delegate) :
  BaseAdapter() {

  init {
    for (i in 0..5) {
      addSection(ArrayList<Any>())
    }
  }

  fun addItems(section: Int, items: List<SampleItem>) {
    addItemOnSection(section, "Section$section")
    addItemListOnSection(section, items)
    notifyDataSetChanged()
  }

  override fun layout(sectionRow: SectionRow): Int {
    return when (sectionRow.row) {
      0 -> R.layout.item_sample1_header
      else -> R.layout.item_sample
    }
  }

  override fun viewHolder(layout: Int, view: View): BaseViewHolder {
    when (layout) {
      R.layout.item_sample1_header -> {
        val binding = ItemSample1HeaderBinding.inflate(LayoutInflater.from(view.context))
        return SampleViewHolder1Header(binding)
      }
      R.layout.item_sample -> {
        val binding = ItemSampleBinding.inflate(LayoutInflater.from(view.context))
        return SampleViewHolder(binding, delegate)
      }
    }
    throw Resources.NotFoundException("not founded layout")
  }
}
