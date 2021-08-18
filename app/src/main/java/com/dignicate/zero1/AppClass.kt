package com.dignicate.zero1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppClass : Application() {

    companion object {

        lateinit var shared: AppClass
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}

val App: AppClass by lazy {
    AppClass.shared
}
