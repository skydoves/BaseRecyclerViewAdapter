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

@Suppress("unused", "MemberVisibilityCanBePrivate")
class SectionRow {
    private var section: Int
    private var row: Int

    constructor() {
        this.section = 0
        this.row = 0
    }

    constructor(section: Int, row: Int) {
        this.section = section
        this.row = row
    }

    fun section(): Int = this.section

    fun row(): Int = this.row

    fun nextSection() {
        this.section++
        this.row = 0
    }

    fun nextRow() = row++
}
