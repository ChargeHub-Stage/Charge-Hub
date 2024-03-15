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

    override fun update(request: CreateLevelRequest) {
        request.id?.let {
            query.updateLevel(
                id = it,
                level = request.level,
                requiredPoints = request.requiredPoints,
            )
        }
    }

    override fun create(request: CreateLevelRequest) {
        query.insertLevel(
            level = request.level,
            requiredPoints = request.requiredPoints,
        )
    }
}