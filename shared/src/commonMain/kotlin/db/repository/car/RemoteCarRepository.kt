package db.repository.car

import db.chargehub.Car
import db.database.car.CarDatabase
import db.database.car.CarDatabaseWrapper
import db.networking.request.CreateCarRequest
import db.repository.GenericRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteCarRepository : GenericRepository<CreateCarRequest, Car, CarDatabase> {


    override val database: CarDatabase = inject<CarDatabaseWrapper>().value.database

    override suspend fun create(request: CreateCarRequest) {
        database.create(request)
    }

    override suspend fun fetchAll() {
        database.getAll()
    }

    override suspend fun fetchById(id: Long) {
        database.getById(id)
    }

    override suspend fun delete(id: Long) {
        database.delete(id)
    }

    override fun findById(id: Long): Flow<Car> {
        return flow { database.getById(id) }
    }

    override fun findAll(): Flow<List<Car>> {
        return flowOf(database.getAll())
    }

    fun findAllBlocking() : List<Car> = database.getAll()

    override suspend fun update(id: Long, request: CreateCarRequest) {
        database.update(id, request)
    }

}