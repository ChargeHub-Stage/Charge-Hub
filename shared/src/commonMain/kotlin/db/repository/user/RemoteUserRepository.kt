package db.repository.user

import FIREBASE_USER_COLLECTION
import db.chargehub.User
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import db.networking.request.CreateUserRequest
import db.networking.request.GetUserRequest
import db.repository.FirebaseRepository
import db.repository.GenericRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.firestore
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject
import org.lighthousegames.logging.logging

class RemoteUserRepository(private val httpClient: HttpClient) :
    GenericRepository<CreateUserRequest, GetUserRequest, UserDatabase> {

    companion object {
        const val USER_COLLECTION = "USERS"
    }

    private val firestore = Firebase.firestore

    private val firebaseRepository: FirebaseRepository by inject()

    override val database: UserDatabase
        get() = inject<UserDatabaseWrapper>().value.database

    override suspend fun create(request: CreateUserRequest) {
        val logging = logging()
        logging.d { "called" }
        firebaseRepository.register(request.email, request.password) {
            logging.d { "registerd :)" }
            // We do NOT store passwords locally or in our firebase user collection
            // Firebase auth handles it themselves
            firestore.collection(USER_COLLECTION).add(request.copy(password = ""))
            database.create(request)
        }
    }

    override suspend fun fetchAll(): List<GetUserRequest> {
        try {
            val userResponse =
                firestore.collection(USER_COLLECTION).get()
            return userResponse.documents.map<DocumentSnapshot, GetUserRequest> { it.data() }
                .onEach { user ->
                    database.update(user.id, user.toCreateUserRequest())
                }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchById(id: String): GetUserRequest {
        try {
            val userDocument =
                firestore.collection(USER_COLLECTION).document(id).get()
            return userDocument.data()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun update(id: String, request: CreateUserRequest) {
        firestore.collection(USER_COLLECTION).document(id).update(request)
        database.update(id, request)
    }

    override suspend fun delete(id: String) {
        firestore.collection(USER_COLLECTION).document(id).delete()
        database.delete(id)
    }

    override fun findById(id: String): Flow<GetUserRequest> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<GetUserRequest>> {
        return flowOf(database.getAll())
    }
}