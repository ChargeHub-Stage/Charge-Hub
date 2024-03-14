package db.repository.user

import db.chargehub.User
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import db.networking.request.CreateUserRequest
import db.networking.request.GetUserRequest
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import db.repository.GenericRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteUserRepository(private val httpClient: HttpClient) :
    GenericRepository<CreateUserRequest, User, UserDatabase> {


    private val databaseWrapper: UserDatabaseWrapper by inject()
    private val firestore = Firebase.firestore
    override suspend fun create(user: CreateUserRequest) {
        databaseWrapper.database.createUser(user)
    override val database: UserDatabase
        get() = inject<UserDatabaseWrapper>().value.database

    override suspend fun create(request: CreateUserRequest) {
        database.create(request)
    }

    override suspend fun fetchAll(): List<GetUserRequest> {
        try {
            val userResponse =
                firestore.collection("USERS").get()
            return userResponse.documents.map { it.data() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchById(id: String) {
        database.getById(id)
    }

    override suspend fun update(id: String, request: CreateUserRequest) {
        database.update(id, request)
    }

    override suspend fun delete(id: String) {
        database.delete(id)
    }

    override fun findById(id: String): Flow<User> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<User>> {
        return flowOf(database.getAll())
    }
}