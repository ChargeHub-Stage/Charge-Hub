package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateReservationRequest(val userId: String, val chargeHubId: String, val carId: String, val startTime: String, val endTime: String)