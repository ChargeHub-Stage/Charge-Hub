package db.repository.car

import db.chargehub.Car
import db.database.car.CarDatabase
import db.database.car.CarDatabaseWrapper
import db.networking.request.CarDataResponse
import db.networking.request.CreateCarRequest
import db.repository.GenericRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteCarRepository : GenericRepository<CreateCarRequest, CarDataResponse, CarDatabase> {


    override val database: CarDatabase = inject<CarDatabaseWrapper>().value.database

    override suspend fun create(request: CreateCarRequest) {
        return database.create(request)
    }

    override suspend fun fetchAll(): List<CarDataResponse> {
        return database.getAll()
    }

    override suspend fun fetchById(id: String): CarDataResponse? {
        return database.getById(id)
    }

    override suspend fun delete(id: String) {
        database.delete(id)
    }

    override fun findById(id: String): Flow<CarDataResponse> {
        return flow { database.getById(id) }
    }

    override fun findAll(): Flow<List<CarDataResponse>> {
        return flowOf(database.getAll())
    }

    override suspend fun update(id: String, request: CreateCarRequest) {
        database.update(id, request)
    }

}