package db.networking.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateLevelRequest(val level: Long, val pointsToNextLevel: Long, val requiredPoints: Long)