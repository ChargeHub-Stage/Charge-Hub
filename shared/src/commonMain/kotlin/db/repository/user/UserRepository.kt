package db.repository.user

import db.chargehub.User
import db.networking.request.CreateUserRequest
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun create(user: CreateUserRequest)

    suspend fun fetchAll()

    suspend fun fetchById(id: Long)

    suspend fun update(id: Long, updatedUser: CreateUserRequest)

    suspend fun delete(id: Long)

    // Local database
    fun findById(id: Long) : Flow<User>

    fun findAll() : Flow<List<User>>

}