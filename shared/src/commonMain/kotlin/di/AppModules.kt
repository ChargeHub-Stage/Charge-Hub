package di

import db.repository.user.RemoteUserRepository
import db.repository.user.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val modules = module {

}

val clientsModule = module {
    single { _ ->
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}

val servicesModule = module {

}

val repositoriesModule = module {
    singleOf(::RemoteUserRepository).bind<UserRepository>()
}