package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateLevelRequest(
    val id: String? = null,
    val level: Long,
    val requiredPoints: Long
)