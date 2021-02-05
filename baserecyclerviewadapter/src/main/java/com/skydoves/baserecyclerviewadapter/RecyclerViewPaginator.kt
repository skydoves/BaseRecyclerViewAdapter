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

@file:Suppress("unused")

package com.skydoves.baserecyclerviewadapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewPaginator(
  recyclerView: RecyclerView,
  private val isLoading: () -> Boolean,
  private val loadMore: (Int) -> Unit,
  private val onLast: () -> Boolean = { true }
) : RecyclerView.OnScrollListener() {

  var threshold: Int = 10
  var currentPage: Int = 0

  var endWithAuto = false

  init {
    recyclerView.addOnScrollListener(this)
  }

  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    super.onScrolled(recyclerView, dx, dy)

    recyclerView.layoutManager?.let {
      val visibleItemCount = it.childCount
      val totalItemCount = it.itemCount
      val lastVisibleItemPosition = when (it) {
        is LinearLayoutManager -> it.findLastVisibleItemPosition()
        is GridLayoutManager -> it.findLastVisibleItemPosition()
        is StaggeredGridLayoutManager -> findLastVisibleItemPosition(it.findLastVisibleItemPositions(null))
        else -> return
      }

      if (onLast() || isLoading()) return

      if (endWithAuto) {
        if (visibleItemCount + lastVisibleItemPosition == totalItemCount) return
      }

      if ((visibleItemCount + lastVisibleItemPosition + threshold) >= totalItemCount) {
        loadMore(++currentPage)
      }
    }
  }

  private fun findLastVisibleItemPosition(lastVisibleItems: IntArray): Int {
    return lastVisibleItems.maxOfOrNull { it } ?: lastVisibleItems[0]
  }

  fun resetCurrentPage() {
    this.currentPage = 0
  }
}
