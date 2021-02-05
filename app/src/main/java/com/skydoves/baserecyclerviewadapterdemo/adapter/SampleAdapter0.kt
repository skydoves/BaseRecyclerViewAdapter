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

import android.view.LayoutInflater
import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.baserecyclerviewadapterdemo.R
import com.skydoves.baserecyclerviewadapterdemo.databinding.ItemSampleBinding
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder

@Suppress("PrivatePropertyName")
class SampleAdapter0(private val delegate: SampleViewHolder.Delegate) :
  BaseAdapter() {

  private val section_item = 0

  init {
    addSection(ArrayList<SampleItem>())
  }

  fun addItems(sampleItems: List<SampleItem>) {
    val previous = sections()[section_item].size
    addItemListOnSection(section_item, sampleItems)
    notifyItemRangeChanged(previous, sampleItems.size)
  }

  override fun layout(sectionRow: SectionRow): Int = R.layout.item_sample

  override fun viewHolder(layout: Int, view: View): BaseViewHolder {
    val binding = ItemSampleBinding.inflate(LayoutInflater.from(view.context))
    return SampleViewHolder(binding, delegate)
  }
}
