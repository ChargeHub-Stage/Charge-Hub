package com.wisemen.chargehub

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)

        initKoin {
            androidContext(this@MainApp)
        }
    }
}