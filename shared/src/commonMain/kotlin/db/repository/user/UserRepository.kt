package db.repository.user

import db.chargehub.User
import db.networking.request.CreateUserRequest
import db.networking.request.GetUserRequest
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun create(user: CreateUserRequest)

    suspend fun fetchAll(): List<GetUserRequest>

    suspend fun fetchById(id: Long)

    suspend fun update(id: Long, updatedUser: CreateUserRequest)

    suspend fun delete(id: Long)

    fun findById(id: Long) : Flow<User>

    fun findAll() : Flow<List<User>>

}