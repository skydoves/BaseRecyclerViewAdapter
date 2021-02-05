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

package com.skydoves.baserecyclerviewadapterdemo.viewholder

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapterdemo.databinding.ItemSampleBinding
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem

class SampleViewHolder(
  private val binding: ItemSampleBinding,
  private val delegate: Delegate
) : BaseViewHolder(binding.root) {

  private lateinit var sampleItem: SampleItem

  interface Delegate {
    fun onItemClick(sampleItem: SampleItem)
  }

  override fun bindData(data: Any) {
    if (data is SampleItem) {
      sampleItem = data
      drawItem()
    }
  }

  private fun drawItem() {
    binding.sample0Avatar.setImageDrawable(sampleItem.image)
    binding.sample0Name.text = sampleItem.name
    binding.sample0Content.text = sampleItem.content
  }

  override fun onClick(v: View?) = delegate.onItemClick(sampleItem)

  override fun onLongClick(v: View?) = false
}
