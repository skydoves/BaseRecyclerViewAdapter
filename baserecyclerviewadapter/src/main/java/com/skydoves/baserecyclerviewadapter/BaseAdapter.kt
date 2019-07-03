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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

/** BaseAdapter is an abstract class for structuring the base adapter class. */
@Suppress("unused", "MemberVisibilityCanBePrivate", "RedundantOverride", "RedundantVisibilityModifier")
abstract class BaseAdapter :
  RecyclerView.Adapter<BaseViewHolder>(), LifecycleObserver {
  /** data holding list attribute. */
  private val sections = ArrayList<MutableList<Any>>()

  /** gets mutable section list. */
  fun sections(): MutableList<MutableList<Any>> {
    return sections
  }

  /** gets mutable an item list on the sections. */
  fun <T> sectionItems(section: Int): MutableList<Any> {
    return sections[section]
  }

  /** adds a section on the section list. */
  fun <T> addSection(section: List<T>) {
    sections().add(ArrayList<Any>(section))
  }

  /** adds a section list on the section list. */
  fun <T> addSectionList(sections: List<List<T>>) {
    for (section in sections) {
      addSection(section)
    }
  }

  /** adds an item on the section. */
  fun addItemOnSection(section: Int, item: Any) {
    sections()[section].add(item)
  }

  /** adds an item list on the section. */
  fun <T> addItemListOnSection(section: Int, items: List<T>) {
    sections()[section].addAll(ArrayList<Any>(items))
  }

  /** removes an item on the section. */
  fun removeItemOnSection(section: Int, item: Any) {
    sections()[section].remove(item)
  }

  /** inserts an section on the section list. */
  fun <T> insertSection(row: Int, section: List<T>) {
    sections().add(row, ArrayList<Any>(section))
  }

  /** gets section count. */
  fun sectionCount(section: Int): Int {
    if (section > sections().size - 1) return 0
    return sections()[section].size
  }

  /** reverses item list on the section. */
  fun <T> reverseSection(section: Int) {
    sections()[section].reverse()
  }

  /** removes a section on the section list. */
  fun <T> removeSection(section: Int) {
    sections().removeAt(section)
  }

  /** clears a section. */
  fun clearSection(section: Int) {
    sections()[section].clear()
  }

  /** clears all sections. */
  fun clearAllSections() {
    sections().clear()
  }

  /** returns layout resources by section rows. */
  protected abstract fun layout(sectionRow: SectionRow): Int

  /** returns [RecyclerView.ViewHolder] by layouts. */
  protected abstract fun viewHolder(@LayoutRes layout: Int, view: View): BaseViewHolder

  override fun onViewDetachedFromWindow(holder: BaseViewHolder) {
    super.onViewDetachedFromWindow(holder)
  }

  override fun onViewAttachedToWindow(holder: BaseViewHolder) {
    super.onViewAttachedToWindow(holder)
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, @LayoutRes layout: Int): BaseViewHolder {
    val view = inflateView(viewGroup, layout)
    return viewHolder(layout, view)
  }

  override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
    val data = objectFromPosition(position)

    try {
      viewHolder.bindData(data)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun getItemViewType(position: Int): Int {
    return layout(sectionRowFromPosition(position))
  }

  /** gets all item counts on the sections. */
  override fun getItemCount(): Int {
    var itemCount = 0
    for (section in sections) {
      itemCount += section.size
    }
    return itemCount
  }

  protected fun objectFromSectionRow(sectionRow: SectionRow): Any {
    return sections[sectionRow.section][sectionRow.row]
  }

  protected fun objectFromPosition(position: Int): Any {
    return objectFromSectionRow(sectionRowFromPosition(position))
  }

  private fun sectionRowFromPosition(position: Int): SectionRow {
    val sectionRow = SectionRow()
    var cursor = 0
    for (section in sections) {
      for (item in section) {
        if (cursor == position) {
          return sectionRow
        }
        cursor++
        sectionRow.nextRow()
      }
      sectionRow.nextSection()
    }

    throw RuntimeException("Position $position not found in sections")
  }

  private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
    val layoutInflater = LayoutInflater.from(viewGroup.context)
    return layoutInflater.inflate(viewType, viewGroup, false)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  public fun onDestroyed() {
    this.clearAllSections()
  }
}
