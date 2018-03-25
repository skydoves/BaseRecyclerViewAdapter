
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

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>(), LifecycleObserver {
    val sections = ArrayList<MutableList<Any>>()

    fun sections(): MutableList<MutableList<Any>> {
        return sections
    }

    fun <T> sectionItems(section: Int): MutableList<Any> {
        return sections[section]
    }

    fun <T> addSection(section: List<T>) {
        sections.add(ArrayList<Any>(section))
    }

    fun <T> addSections(sections: List<List<T>>) {
        for (section in sections) {
            addSection(section)
        }
    }

    fun addItemOnSection(section: Int, item: Any) {
        sections[section].add(item)
    }

    fun <T> addItemsOnSection(section: Int, items: List<T>) {
        sections[section].addAll(ArrayList<Any>(items))
    }

    fun <T> setSection(row: Int, section: List<T>) {
        sections[row] = ArrayList<Any>(section)
    }

    fun <T> insertSection(row: Int, section: List<T>) {
        sections.add(row, ArrayList<Any>(section))
    }

    fun <T> sectionOrderChange(row: Int) {
        Collections.reverse(sections[row])
    }

    fun <T> removeSection(location: Int) {
        sections.removeAt(location)
    }

    fun clearSections() {
        sections.clear()
    }

    protected abstract fun layout(sectionRow: SectionRow): Int

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

    override fun getItemCount(): Int {
        var itemCount = 0
        for (section in sections) {
            itemCount += section.size
        }

        return itemCount
    }

    protected fun objectFromSectionRow(sectionRow: SectionRow): Any {
        return sections[sectionRow.section()][sectionRow.row()]
    }

    protected fun sectionCount(section: Int): Int {
        if (section > sections().size - 1) return 0
        else return sections()[section].size
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
        this.clearSections()
    }
}