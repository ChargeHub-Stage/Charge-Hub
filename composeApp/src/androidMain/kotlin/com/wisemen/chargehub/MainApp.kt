package com.wisemen.chargehub

import android.app.Application
import di.modules
import di.clientsModule
import di.platformModules
import di.repositoriesModule
import di.servicesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApp)
            modules(modules, clientsModule, servicesModule, repositoriesModule, platformModules())
        }
    }

}