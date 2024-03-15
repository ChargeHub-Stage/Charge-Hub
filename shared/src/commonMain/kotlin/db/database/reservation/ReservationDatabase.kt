package db.database.reservation

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.Reservation
import db.database.GenericDatabaseOperations
import db.networking.request.CreateReservationRequest

class ReservationDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<Reservation, CreateReservationRequest>(sqlDriver) {

    private val query
        get() = database.reservationDbQueries

    override fun getAll(): List<Reservation> {
        return query.getAllReservations().executeAsList()
    }

    override fun getById(id: String): Reservation {
        return query.getReservationById(id).executeAsOne()
    }

    override fun delete(id: String) {
        query.deleteReservation(id)
    }

    override fun update(request: CreateReservationRequest) {
        request.id?.let {
            query.updateReservation(
                id = it,
                userId = request.userId,
                carId = request.carId,
                chargeHubId = request.chargeHubId,
                startTime = request.startTime,
                endTime = request.endTime
            )
        }
    }

    override fun create(request: CreateReservationRequest) {
        query.insertReservation(
            userId = request.userId,
            carId = request.carId,
            chargeHubId = request.chargeHubId,
            startTime = request.startTime,
            endTime = request.endTime
        )
    }
}