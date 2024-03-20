package networking

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class CarConnectService : KoinComponent {

    private val httpClient: HttpClient by inject()

    suspend fun getCarInfo(carId: String) : String {
        return httpClient.get("https://carconnect.io/api/v1/cars/$carId").bodyAsText()
    }

}