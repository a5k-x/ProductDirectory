package com.ask.productdirectory

import android.app.Application
import com.ask.productdirectory.di.DaggerAppComponent

class App: Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}