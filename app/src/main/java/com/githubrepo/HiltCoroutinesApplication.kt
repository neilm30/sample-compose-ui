package com.githubrepo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltCoroutinesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}