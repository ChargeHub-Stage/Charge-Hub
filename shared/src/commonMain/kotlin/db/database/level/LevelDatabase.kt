package db.database.level

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.Level
import db.database.GenericDatabaseOperations
import db.networking.request.CreateLevelRequest

class LevelDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<Level, CreateLevelRequest>(sqlDriver) {

    private val query
        get() = database.levelDbQueries

    override fun getAll(): List<Level> {
        return query.getAllLevels().executeAsList()
    }

    override fun getById(id: String): Level {
        return query.getLevelById(id).executeAsOne()
    }

    override fun delete(id: String) {
        query.deleteLevel(id)
    }

    override fun update(id: String, request: CreateLevelRequest) {
        query.updateLevel(
            id = id,
            level = request.level,
            requiredPoints = request.requiredPoints,
            pointsToNextLevel = request.pointsToNextLevel
        )
    }

    override fun create(request: CreateLevelRequest) {
        query.insertLevel(
            level = request.level,
            requiredPoints = request.requiredPoints,
            pointsToNextLevel = request.pointsToNextLevel
        )
    }
}