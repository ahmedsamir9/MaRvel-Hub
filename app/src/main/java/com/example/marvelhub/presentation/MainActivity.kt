package com.example.marvelhub.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.work.*
import com.example.marvelhub.databinding.ActivityMainBinding
import com.example.marvelhub.application.workers.DeleteWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WorkManager.getInstance(this).enqueue(makeDeleteDataRequest())
    }

    private fun deleteWorkConstraint(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }
    private fun makeDeleteDataRequest(): PeriodicWorkRequest {
        return  PeriodicWorkRequestBuilder<DeleteWorker>(5,TimeUnit.HOURS)
            .setConstraints(deleteWorkConstraint())
            .setInitialDelay(30,TimeUnit.MINUTES)
            .build()
    }
}