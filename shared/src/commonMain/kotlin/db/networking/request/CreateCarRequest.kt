package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateCarRequest(
    val id: String,
    val userId: String,
    val brand: String,
    val model: String,
    val year: Int,
    val percentage: Int,
    val range: Int,
    val pluggedIn: Boolean,
    val charging: Boolean,
    val powerComputed: Int,
    val minutesLeft: Int,
    val chargeLimit: Int
)