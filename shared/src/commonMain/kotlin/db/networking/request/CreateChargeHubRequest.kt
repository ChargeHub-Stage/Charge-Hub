package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
class CreateChargeHubRequest(
    val id: String? = null,
    val name: String
)