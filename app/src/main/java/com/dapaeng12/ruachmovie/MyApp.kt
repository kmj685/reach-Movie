package com.dapaeng12.ruachmovie

import android.app.Application
import android.content.Context

class MyApp : Application() {
    companion object {
        lateinit var appContext: Context
        private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}