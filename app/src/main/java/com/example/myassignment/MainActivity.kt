package com.example.myassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myassignment.Data.SharedPrefrence


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPrefrence.init(this)
    }
}