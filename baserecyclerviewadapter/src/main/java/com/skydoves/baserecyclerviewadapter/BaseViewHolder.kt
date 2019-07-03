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

package com.skydoves.baserecyclerviewadapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/** BaseViewHolder is an abstract class for structuring the base view holder class. */
@Suppress("unused", "LeakingThis")
abstract class BaseViewHolder(private val view: View)
  : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {

  init {
    view.setOnClickListener(this)
    view.setOnLongClickListener(this)
  }

  /** binds data to the view holder class. */
  @Throws(Exception::class)
  abstract fun bindData(data: Any)

  /** gets the view of the [RecyclerView.ViewHolder]. */
  fun view(): View {
    return view
  }

  /** gets the context. */
  fun context(): Context {
    return view.context
  }
}
