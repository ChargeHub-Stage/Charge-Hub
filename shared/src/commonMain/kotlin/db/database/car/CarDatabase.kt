package db.database.car

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.Car
import db.chargehub.CarDbQueries
import db.database.GenericDatabaseOperations
import db.networking.request.CarDataResponse
import db.networking.request.CreateCarRequest
import kotlinx.serialization.descriptors.PrimitiveKind

class CarDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<CarDataResponse, CreateCarRequest>(sqlDriver) {
    private val query: CarDbQueries
        get() = database.carDbQueries

    override fun getAll(): List<CarDataResponse> {
        return query.getAllCars().executeAsList().map { it.mapToCarDataResponse() }
    }

    override fun getById(id: String): CarDataResponse? {
        return query.getCarByVin(id).executeAsOneOrNull()?.mapToCarDataResponse()
    }

    override fun delete(id: String) {

        query.deleteCar(id)
    }

    override fun update(id: String, request: CreateCarRequest) {
        query.updateCar(
            id = id,
            userId = request.userId,
            brand = request.brand,
            vin = request.vin,
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
            userId = request.userId,
            brand = request.brand,
            vin = request.vin,
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
            vin = vin,
            model = model,
            year = year.toInt(),
            brand = brand,
            userId = userId
        )
    }


}