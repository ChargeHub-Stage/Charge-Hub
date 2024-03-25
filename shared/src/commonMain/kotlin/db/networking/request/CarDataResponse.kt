package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CarDataResponse(
    val id: String = "",
    val userId: String = "",
    val vin: String = "",
    val brand: String = "",
    val model: String = "",
    val year: Int = -1,
    val status: StatusResponse? = null
) {
    fun toCreateCarRequest(id : String): CreateCarRequest {
        return CreateCarRequest(
            id = id,
            userId = userId,
            brand = brand,
            model = model,
            year = year,
            percentage = status?.point?.battery?.percentage ?: -1,
            range = status?.point?.battery?.range ?: -1,
            pluggedIn = status?.point?.charging?.pluggedIn ?: false,
            charging = status?.point?.charging?.charging ?: false,
            powerComputed = status?.point?.charging?.powerComputed ?: -1,
            minutesLeft = status?.point?.charging?.minutesLeft ?: -1,
            chargeLimit = status?.point?.charging?.chargeLimit ?: -1
        )
    }
}

@Serializable
data class StatusResponse(
    val point: PointResponse?
)

@Serializable
data class PointResponse(
    val charging: ChargerPointResponse? = null,
    val battery: BatteryPointResponse? = null
)

@Serializable
data class BatteryPointResponse(
    val percentage: Int?,
    val range: Int?
)

@Serializable
data class ChargerPointResponse(
    val pluggedIn: Boolean,
    val charging: Boolean,
    val powerComputed: Int?,
    val minutesLeft: Int?,
    val chargeLimit: Int?
)
