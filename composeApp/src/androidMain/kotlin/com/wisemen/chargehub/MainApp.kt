package com.wisemen.chargehub

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApp)
        }


    }


}