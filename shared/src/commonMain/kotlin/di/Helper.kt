package di

import ResourceStrings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

private val allModules =
    listOf(modules, clientsModule, servicesModule, repositoriesModule, platformModules())

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

class StringsHelper : KoinComponent {
    private val strings: ResourceStrings by inject()

    fun getResourceStrings(): ResourceStrings = strings
}