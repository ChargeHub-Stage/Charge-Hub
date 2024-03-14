package di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

private val allModules = listOf(modules, clientsModule, servicesModule, repositoriesModule, platformModules())
fun initKoin(koinAppDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        koinAppDeclaration()
        modules(allModules)
    }
}

fun initKoinIos() {
    startKoin {
        modules(allModules)
    }
}