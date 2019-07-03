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
import kotlinx.android.synthetic.main.item_sample1_header.view.*

@Suppress("ClassName", "CanBeParameter")
class SampleViewHolder1_Header(private val view: View)
  : BaseViewHolder(view) {

  override fun bindData(data: Any) {
    if (data is String) {
      itemView.sample1_header.text = data
    }
  }

  override fun onClick(v: View?) = Unit

  override fun onLongClick(v: View?) = false
}
