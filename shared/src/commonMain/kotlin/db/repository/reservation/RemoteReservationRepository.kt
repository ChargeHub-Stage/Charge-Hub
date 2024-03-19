package db.repository.reservation

import db.chargehub.Reservation
import db.database.reservation.ReservationDatabase
import db.database.reservation.ReservationDatabaseWrapper
import db.networking.request.CreateReservationRequest
import db.repository.GenericRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteReservationRepository :
    GenericRepository<CreateReservationRequest, Reservation, ReservationDatabase> {
    override val database: ReservationDatabase
        get() = inject<ReservationDatabaseWrapper>().value.database

    override suspend fun fetchAll(): List<Reservation> {
        return database.getAll()
    }

    override suspend fun fetchById(id: String): Reservation {
        return database.getById(id)
    }

    override suspend fun delete(id: String) {
        database.delete(id)
    }

    override fun findById(id: String): Flow<Reservation> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<Reservation>> {
        return flowOf(database.getAll())
    }

    override suspend fun update(id: String, request: CreateReservationRequest) {
        database.update(id, request)
    }

    override suspend fun create(request: CreateReservationRequest) {
        database.create(request)
    }
}