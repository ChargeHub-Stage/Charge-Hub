package db.repository

import db.chargehub.User
import db.networking.request.CreateUserRequest
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

/**
 *  A Generic repository interface.
 *  [T] represents the returning type of functions, usually the models.
 *  [R] represents the "Request" type passed in parameters.
 *  [D] represents the database for this repository.
 */
interface GenericRepository<R, T, D> : KoinComponent {

    val database: D

    suspend fun create(request: R)

    suspend fun fetchAll()

    suspend fun fetchById(id: Long)

    suspend fun update(id: Long, request: R)

    suspend fun delete(id: Long)

    // Local database
    fun findById(id: Long) : Flow<T>

    fun findAll() : Flow<List<T>>

}