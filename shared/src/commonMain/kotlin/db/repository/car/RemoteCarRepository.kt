package db.repository.car

import db.chargehub.Car
import db.database.car.CarDatabase
import db.database.car.CarDatabaseWrapper
import db.networking.request.CreateCarRequest
import db.repository.GenericRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject

class RemoteCarRepository : GenericRepository<CreateCarRequest, Car, CarDatabase> {


    override val database: CarDatabase = inject<CarDatabaseWrapper>().value.database

    override suspend fun create(request: CreateCarRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAll() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): Flow<Car> {
        TODO("Not yet implemented")
    }

    override fun findAll(): Flow<List<Car>> {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: Long, request: CreateCarRequest) {
        TODO("Not yet implemented")
    }

}