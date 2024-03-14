package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateCarRequest(val userId: Long, val brand: String, val plate: String)