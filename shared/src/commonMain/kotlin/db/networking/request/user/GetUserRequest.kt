package db.networking.request.user

import kotlinx.serialization.Serializable

@Serializable
class GetUserRequest (
    val name: String,
    val email: String,
    val password: String,
) : UserRequest