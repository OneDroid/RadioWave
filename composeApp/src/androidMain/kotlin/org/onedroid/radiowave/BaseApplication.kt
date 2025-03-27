package org.onedroid.radiowave

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.onedroid.radiowave.app.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BaseApplication)
        }
    }
}