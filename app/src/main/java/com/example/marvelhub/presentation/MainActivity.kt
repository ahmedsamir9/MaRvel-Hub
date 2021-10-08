package com.example.marvelhub.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelhub.R
import android.view.WindowManager

import android.view.Window

import android.os.Build
import com.example.marvelhub.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}