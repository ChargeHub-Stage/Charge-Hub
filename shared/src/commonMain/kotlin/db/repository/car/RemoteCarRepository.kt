package db.repository.car

import db.database.car.CarDatabase
import db.database.car.CarDatabaseWrapper
import db.database.networkcalls.CarConnectNetworkCallsDatabase
import db.database.networkcalls.CarConnectNetworkCallsWrapper
import db.networking.request.CarDataResponse
import db.networking.request.CreateCarRequest
import db.repository.FirebaseRepository
import db.repository.GenericRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import networking.CarConnectService
import org.koin.core.component.inject

class RemoteCarRepository : GenericRepository<CreateCarRequest, CarDataResponse, CarDatabase> {

    companion object {
        const val COLLECTION_NAME = "CARS"
    }

    private val firestore = Firebase.firestore

    private val networkCallDatabase: CarConnectNetworkCallsDatabase =
        inject<CarConnectNetworkCallsWrapper>().value.database
    private val firebaseRepository: FirebaseRepository by inject()
    private val carConnectService: CarConnectService by inject()
    override val database: CarDatabase = inject<CarDatabaseWrapper>().value.database

    override suspend fun create(request: CreateCarRequest) {
        firestore.collection(COLLECTION_NAME).document(request.id).set(request)
        database.create(request.copy(userId = firebaseRepository.getCurrentUserUid()))
    }

    override suspend fun fetchAll(): List<CarDataResponse> {
        try {
            val userResponse =
                firestore.collection(COLLECTION_NAME).get()
            return userResponse.documents.map<DocumentSnapshot, CarDataResponse> { it.data() }
                .onEach { response ->
                    val request = response.toCreateCarRequest(id = response.id)
                    database.update(response.id, request)
                }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchById(id: String): CarDataResponse? {
        return database.getById(id)
    }


    override suspend fun delete(id: String) {
        firestore.collection(COLLECTION_NAME).document(id).delete()
        database.delete(id)
    }

    override fun findById(id: String): Flow<CarDataResponse> {
        return flow { database.getById(id) }
    }

    override fun findAll(): Flow<List<CarDataResponse>> {
        return flowOf(database.getAll())
    }

    override suspend fun update(id: String, request: CreateCarRequest) {
        firestore.collection(COLLECTION_NAME).document(id).update(request)
        database.update(id, request)
    }

    suspend fun fetchCarFromUser(userId: String) =
        flowOf(database.getCarByUserId(userId).also { fetchById(it?.vin ?: "") })

    suspend fun fetchCarConnectData(vin: String): CarDataResponse? {
        return if (networkCallDatabase.createOrUpdateNetworkCall()) {
            val carConnectResponse = carConnectService.getCarInfo(vin)
            val request = carConnectResponse.toCreateCarRequest(id = vin)
            if (fetchById(vin) == null) {
                create(request)
            } else update(vin, request)
            return carConnectResponse
        } else fetchById(vin)
    }
}