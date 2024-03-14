package db.repository.user

import db.chargehub.User
import db.database.user.UserDatabaseWrapper
import db.networking.request.CreateUserRequest
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteUserRepository(private val httpClient: HttpClient) : UserRepository, KoinComponent {

    private val databaseWrapper: UserDatabaseWrapper by inject()
    override suspend fun create(user: CreateUserRequest) {
        databaseWrapper.database.createUser(user)
    }

    override suspend fun fetchAll() {
        databaseWrapper.database.getAll()
    }

    override suspend fun fetchById(id: Long) {
        databaseWrapper.database.fetchById(id)
    }

    override suspend fun update(id: Long, updatedUser: CreateUserRequest) {
        databaseWrapper.database.update(id, updatedUser)
    }

    override suspend fun delete(id: Long) {
        databaseWrapper.database.delete(id)
    }

    override fun findById(id: Long): Flow<User> {
        return flow { databaseWrapper.database.fetchById(id) }
    }

    override fun findAll(): Flow<List<User>> {
        return flow { databaseWrapper.database.getAll() }
    }
}