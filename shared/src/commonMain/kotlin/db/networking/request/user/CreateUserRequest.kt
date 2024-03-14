package db.networking.request.user

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val levelId: String,
    val name: String,
    val email: String,
    val password: String,
    val currentPoints: Long
) : UserRequest