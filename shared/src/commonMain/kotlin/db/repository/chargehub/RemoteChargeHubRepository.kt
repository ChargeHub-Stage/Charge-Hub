package db.repository.chargehub

import db.chargehub.ChargeHub
import db.database.chargehub.ChargeHubDatabase
import db.database.chargehub.ChargeHubDatabaseWrapper
import db.networking.request.CreateChargeHubRequest
import db.repository.GenericRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteChargeHubRepository : GenericRepository<CreateChargeHubRequest, ChargeHub, ChargeHubDatabase> {
    override val database: ChargeHubDatabase
        get() = inject<ChargeHubDatabaseWrapper>().value.database

    override suspend fun fetchAll(): List<ChargeHub> {
        return database.getAll()
    }

    override suspend fun fetchById(id: String): ChargeHub {
        return database.getById(id)
    }

    override suspend fun delete(id: String) {
        database.delete(id)
    }

    override fun findById(id: String): Flow<ChargeHub> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<ChargeHub>> {
        return flowOf(database.getAll())
    }

    override suspend fun update(id: String, request: CreateChargeHubRequest) {
       database.update(id = id, request)
    }

    override suspend fun create(request: CreateChargeHubRequest) {
        database.create(request)
    }
}