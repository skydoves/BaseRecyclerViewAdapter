package com.skydoves.baserecyclerviewadapterdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.skydoves.baserecyclerviewadapterdemo.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 * Developed by skydoves on 2018-03-25.
 * Copyright (c) 2018 skydoves rights reserved.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0.setOnClickListener { startActivity<SampleActivity0>() }
        button1.setOnClickListener { startActivity<SampleActivity1>() }
        button2.setOnClickListener { startActivity<SampleActivity2>() }
    }
}
