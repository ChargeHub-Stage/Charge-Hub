package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val levelId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val currentPoints: Long,
    val carId: String
)