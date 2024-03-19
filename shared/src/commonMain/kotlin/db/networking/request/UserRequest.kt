package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val levelId: String,
    val name: String,
    val email: String,
    val password: String,
    val currentPoints: Long
)