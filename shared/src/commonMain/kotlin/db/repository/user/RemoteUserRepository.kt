package db.repository.user

import FIREBASE_USER_COLLECTION
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
    GenericRepository<UserRequest, User, UserDatabase> {

    private val firestore = Firebase.firestore
    override val database: UserDatabase
        get() = inject<UserDatabaseWrapper>().value.database

    override suspend fun create(request: UserRequest) {
        firestore.collection(FIREBASE_USER_COLLECTION).add(request)
        database.create(request)
    }

    override suspend fun fetchAll(): List<User> {
        try {
            val userResponse =
                firestore.collection(FIREBASE_USER_COLLECTION).get()
            return userResponse.documents.map { it.data() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchById(id: String): User {
       try {
           val userDocument =
               firestore.collection(FIREBASE_USER_COLLECTION).document(id).get()
           return userDocument.data()
       } catch(e: Exception) {
           throw e
       }
    }

    override suspend fun update(id: String, request: UserRequest) {
        firestore.collection(FIREBASE_USER_COLLECTION).document(id).update(request)
        database.update(id, request)
    }

    override suspend fun delete(id: String) {
        firestore.collection(FIREBASE_USER_COLLECTION).document(id).delete()
        database.delete(id)
    }

    override fun findById(id: String): Flow<User> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<User>> {
        return flowOf(database.getAll())
    }
}