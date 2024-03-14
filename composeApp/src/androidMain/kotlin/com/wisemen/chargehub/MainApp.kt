package com.wisemen.chargehub

import android.app.Application
import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import di.modules
import di.clientsModule
import di.initKoin
import di.platformModules
import di.repositoriesModule
import di.servicesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApp)
        }


    }


}