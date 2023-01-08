package com.samplecode.example

import android.app.Application
import com.samplecode.lib.ApplicationUtil

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationUtil.init(this)
    }
}