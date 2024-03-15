package db.repository.user

import db.chargehub.User
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import db.networking.request.UserRequest
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import db.repository.GenericRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteUserRepository(private val httpClient: HttpClient) :
    GenericRepository<UserRequest, UserRequest, UserDatabase> {

    private val firestore = Firebase.firestore
    override val database: UserDatabase
        get() = inject<UserDatabaseWrapper>().value.database

    override suspend fun create(request: UserRequest) {
        val firebaseUser = firestore.collection("USERS").add(request)
        database.create(request.copy(id = firebaseUser.id))
    }

    override suspend fun fetchAll(): List<UserRequest> {
        try {
            val userResponse =
                firestore.collection("USERS").get()
            return userResponse.documents.map { it.data() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchById(id: String): UserRequest {
       try {
           val userDocument =
               firestore.collection("USERS").document(id).get()
           return userDocument.data()
       } catch(e: Exception) {
           throw e
       }
    }

    override suspend fun update(request: UserRequest) {
        request.id?.let { firestore.collection("USERS").document(it).update(request) }
        database.update(request.copy(id = request.id))
    }

    override suspend fun delete(id: String) {
        firestore.collection("USERS").document(id).delete()
    }

    override fun findById(id: String): Flow<UserRequest> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<UserRequest>> {
        return flowOf(database.getAll())
    }
}