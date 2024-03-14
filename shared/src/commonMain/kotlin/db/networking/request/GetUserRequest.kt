package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
class GetUserRequest (
    val name: String,
    val email: String,
    val password: String,
)