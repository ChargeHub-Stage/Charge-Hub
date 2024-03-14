package db.database.car

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.Car
import db.chargehub.CarDbQueries
import db.database.GenericDatabaseOperations
import db.networking.request.CreateCarRequest

class CarDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<Car, CreateCarRequest>(sqlDriver) {
    private val query: CarDbQueries
        get() = database.carDbQueries

    override fun getAll(): List<Car> {
        return query.getAllCars().executeAsList()
    }

    override fun getById(id: Long): Car {
        return query.getCarById(id).executeAsOne()
    }

    override fun delete(id: Long) {
        query.deleteCar(id)
    }

    override fun update(id: Long, request: CreateCarRequest) {
        query.updateCar(
            id = id,
            brand = request.brand,
            plate = request.plate,
            userId = request.userId
        )
    }

    override fun create(request: CreateCarRequest) {
        query.insertCar(
            userId = request.userId,
            brand = request.brand,
            plate = request.plate
        )
    }


}