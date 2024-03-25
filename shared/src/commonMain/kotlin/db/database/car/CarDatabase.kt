package db.database.car

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.Car
import db.chargehub.CarDbQueries
import db.database.GenericDatabaseOperations
import db.networking.request.CarDataResponse
import db.networking.request.CreateCarRequest

class CarDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<CarDataResponse, CreateCarRequest>(sqlDriver) {
    private val query: CarDbQueries
        get() = database.carDbQueries

    override fun getAll(): List<CarDataResponse> {
        return query.getAllCars().executeAsList().map { it.mapToCarDataResponse() }
    }

    // The ID of a car in the localdatabase is equal to the VIN in the car connect database
    override fun getById(id: String): CarDataResponse? {
        return query.getCarById(id).executeAsOneOrNull()?.mapToCarDataResponse()
    }

    fun getCarByUserId(userId: String): CarDataResponse? {
        return query.getCarByUser(userId).executeAsOneOrNull()?.mapToCarDataResponse()
    }

    override fun delete(id: String) {

        query.deleteCar(id)
    }

    override fun update(id: String, request: CreateCarRequest) {
        query.updateCar(
            id = id,
            userId = request.userId,
            brand = request.brand,
            year = request.year.toLong(),
            model = request.model,
            chargeLimit = request.chargeLimit.toLong(),
            charging = request.charging,
            percentage = request.percentage.toLong(),
            minutesLeft = request.minutesLeft.toLong(),
            pluggedIn = request.pluggedIn,
            powerComputed = request.powerComputed.toLong()
        )
    }

    override fun create(request: CreateCarRequest) {
        query.insertCar(
            id = request.id,
            userId = request.userId,
            brand = request.brand,
            year = request.year.toLong(),
            model = request.model,
            chargeLimit = request.chargeLimit.toLong(),
            charging = request.charging,
            percentage = request.percentage.toLong(),
            minutesLeft = request.minutesLeft.toLong(),
            pluggedIn = request.pluggedIn,
            powerComputed = request.powerComputed.toLong()
        )
    }

    private fun Car.mapToCarDataResponse(): CarDataResponse {
        return CarDataResponse(
            id = id,
            model = model,
            year = year.toInt(),
            brand = brand,
            userId = userId
        )
    }


}