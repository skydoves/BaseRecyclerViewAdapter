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

package com.skydoves.baserecyclerviewadapterdemo

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.skydoves.baserecyclerviewadapterdemo.model.SampleItem
import java.util.ArrayList
import java.util.Random

class MockSamples {
  companion object {
    fun mockSampleItems(context: Context, size: Int): List<SampleItem> {
      val samples = ArrayList<SampleItem>()
      for (i in 0..(size - 1)) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_person_black_24dp)
        drawable?.let {
          samples.add(SampleItem(drawable, "sample$i", "This is a sample content$i"))
        }
      }
      return samples
    }

    fun mockSampleItemsRandom(context: Context, offset: Int, size: Int): List<SampleItem> {
      val samples = ArrayList<SampleItem>()
      for (i in offset..(offset + size - 1)) {
        samples.add(SampleItem(getRandomDrawable(context), "sample$i", "This is a sample content$i"))
      }
      return samples
    }

    fun getRandomDrawable(context: Context): Drawable {
      val random = Random()
      when (random.nextInt(3)) {
        0 -> return ContextCompat.getDrawable(context, R.drawable.face1)!!
        1 -> return ContextCompat.getDrawable(context, R.drawable.face2)!!
        else -> return ContextCompat.getDrawable(context, R.drawable.face3)!!
      }
    }
  }
}
