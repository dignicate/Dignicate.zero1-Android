package com.dignicate.zero1

import android.app.Application

class AppClass : Application() {

    companion object {

        lateinit var shared: AppClass
    }
}

val App: AppClass by lazy {
    AppClass.shared
}
