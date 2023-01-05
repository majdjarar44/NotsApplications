package com.mcit.notsapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcit.notsapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}