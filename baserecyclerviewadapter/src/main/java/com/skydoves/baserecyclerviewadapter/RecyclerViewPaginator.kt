
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

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class RecyclerViewPaginator(private val recyclerView: RecyclerView,
                            private val isLoading: () -> Boolean,
                            private val loadMore: (Int) -> Unit,
                            private val onLast: () -> Boolean = { true }): RecyclerView.OnScrollListener() {

    private var threshold = 10
    private var currentPage: Int = 0

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager
        val visibleItemCount = recyclerView.layoutManager.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        val firstVisibleItemPosition = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            else -> return
        }

        if (onLast() || isLoading()) return

        if ((visibleItemCount + firstVisibleItemPosition + threshold) >= totalItemCount) {
            loadMore(++currentPage)
        }
    }

    fun resetCurrentPage() {
        this.currentPage = 0
    }

    fun setThreshold(threshold: Int) {
        this.threshold = threshold
    }
}