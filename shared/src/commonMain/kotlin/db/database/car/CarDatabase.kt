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

    override fun getById(id: String): Car {
        return query.getCarById(id).executeAsOne()
    }

    override fun delete(id: String) {
        query.deleteCar(id)
    }

    override fun update(request: CreateCarRequest) {
        request.id?.let {
            query.updateCar(
                id = it,
                brand = request.brand,
                plate = request.plate,
                userId = request.userId
            )
        }
    }

    override fun create(request: CreateCarRequest) {
        query.insertCar(
            userId = request.userId,
            brand = request.brand,
            plate = request.plate
        )
    }


}