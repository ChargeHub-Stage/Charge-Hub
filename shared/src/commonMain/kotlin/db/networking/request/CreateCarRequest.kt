package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateCarRequest(val userId: String, val brand: String, val plate: String)