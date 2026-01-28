package com.dapaeng12.ruachmovie

import android.app.Application
import java.time.Instant

class App: Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}