package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
class CreateChargeHubRequest(val name: String)