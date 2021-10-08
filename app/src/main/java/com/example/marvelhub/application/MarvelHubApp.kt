package com.example.marvelhub.application

import android.app.Application
import com.example.marvelhub.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
@HiltAndroidApp
class MarvelHubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}