package db.repository.user

import db.chargehub.User
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import db.networking.request.CreateUserRequest
import db.repository.GenericRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteUserRepository(private val httpClient: HttpClient) :
    GenericRepository<CreateUserRequest, User, UserDatabase> {


    override val database: UserDatabase
        get() = inject<UserDatabaseWrapper>().value.database

    override suspend fun create(request: CreateUserRequest) {
        database.create(request)
    }

    override suspend fun fetchAll() {
        database.getAll()
    }

    override suspend fun fetchById(id: Long) {
        database.getById(id)
    }

    override suspend fun update(id: Long, request: CreateUserRequest) {
        database.update(id, request)
    }

    override suspend fun delete(id: Long) {
        database.delete(id)
    }

    override fun findById(id: Long): Flow<User> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<User>> {
        return flowOf(database.getAll())
    }
}