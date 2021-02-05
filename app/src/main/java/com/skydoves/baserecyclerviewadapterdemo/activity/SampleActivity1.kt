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

package com.skydoves.baserecyclerviewadapterdemo.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.baserecyclerviewadapterdemo.MockSamples
import com.skydoves.baserecyclerviewadapterdemo.adapter.SampleAdapter1
import com.skydoves.baserecyclerviewadapterdemo.databinding.ActivitySample1Binding
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import com.skydoves.baserecyclerviewadapterdemo.viewholder.SampleViewHolder

class SampleActivity1 : AppCompatActivity(), SampleViewHolder.Delegate {

  private val adapter by lazy { SampleAdapter1(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivitySample1Binding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.sample1RecyclerView.adapter = adapter

    for (i in 0..5) {
      adapter.addItems(i, MockSamples.mockSampleItems(this, 3))
    }
  }

  override fun onItemClick(sampleItem: SampleItem) {
    Toast.makeText(this, sampleItem.name, Toast.LENGTH_SHORT).show()
  }
}
