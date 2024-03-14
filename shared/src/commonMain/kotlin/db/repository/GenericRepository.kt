package db.repository

import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

/**
 *  A Generic repository interface.
 *  [T] represents the returning type of functions, usually the models.
 *  [R] represents the "Request" type passed in parameters.
 *  [D] represents the database for this repository.
 */
interface GenericRepository<R, T, D> : KoinComponent {

    val database: D

    suspend fun create(request: R)

    suspend fun fetchAll() : List<T>

    suspend fun fetchById(id: String)

    suspend fun update(id: String, request: R)

    suspend fun delete(id: String)

    // Local database
    fun findById(id: String): Flow<T>

    fun findAll(): Flow<List<T>>

}