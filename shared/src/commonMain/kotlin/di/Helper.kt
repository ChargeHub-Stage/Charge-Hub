package di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(modules, clientsModule, servicesModule, repositoriesModule, platformModules())
    }
}