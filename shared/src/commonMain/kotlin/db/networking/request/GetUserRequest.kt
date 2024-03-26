package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class GetUserRequest(
    val id: String,
    val levelId: String,
    val carId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val currentPoints: Long,
) {

    fun toCreateUserRequest() = CreateUserRequest(
        levelId = levelId,
        carId = carId,
        firstName = firstName,
        lastName = lastName,
        email = email,
        currentPoints = currentPoints,
        password = ""
    )

}