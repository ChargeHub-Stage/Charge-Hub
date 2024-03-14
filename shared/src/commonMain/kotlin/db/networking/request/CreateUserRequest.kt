package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(val levelId: Long, val name: String, val email: String, val password: String, val currentPoints: Long)