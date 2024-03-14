package db.repository.level

import db.chargehub.Level
import db.database.level.LevelDatabase
import db.database.level.LevelDatabaseWrapper
import db.networking.request.CreateLevelRequest
import db.repository.GenericRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteLevelRepository : GenericRepository<CreateLevelRequest, Level, LevelDatabase> {

    override val database: LevelDatabase
        get() = inject<LevelDatabaseWrapper>().value.database

    override suspend fun fetchAll() {
        database.getAll()
    }

    override suspend fun fetchById(id: String) {
       database.getById(id)
    }

    override suspend fun delete(id: String) {
        database.delete(id)
    }

    override fun findById(id: String): Flow<Level> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<Level>> {
        return flowOf(database.getAll())
    }

    override suspend fun update(id: String, request: CreateLevelRequest) {
        database.update(id, request)
    }

    override suspend fun create(request: CreateLevelRequest) {
        database.create(request)
    }
}