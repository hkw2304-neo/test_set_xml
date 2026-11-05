package com.hkw.a1016_socialservice

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}