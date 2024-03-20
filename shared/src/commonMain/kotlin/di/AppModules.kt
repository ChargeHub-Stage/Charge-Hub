package di

import db.repository.car.RemoteCarRepository
import db.repository.chargehub.RemoteChargeHubRepository
import db.repository.level.RemoteLevelRepository
import db.repository.networkcalls.RemoteCarConnectNetworkCallsRepository
import db.repository.reservation.RemoteReservationRepository
import db.repository.user.RemoteUserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import networking.CarConnectService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import screens.landing.LandingScreenViewModel
import screens.register.RegisterScreenViewModel

val modules = module {
    singleOf(::LandingScreenViewModel)
    singleOf(::RegisterScreenViewModel)
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
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(accessToken = Secrets.API_TOKEN, "...")
                    }
                }
            }
        }
    }
}

val servicesModule = module {
    singleOf(::CarConnectService)
}

val repositoriesModule = module {
    singleOf(::RemoteUserRepository)
    singleOf(::RemoteCarRepository)
    singleOf(::RemoteChargeHubRepository)
    singleOf(::RemoteLevelRepository)
    singleOf(::RemoteReservationRepository)
    singleOf(::RemoteCarConnectNetworkCallsRepository)
}