package db.repository.level

import FIREBASE_LEVEL_COLLECTION
import db.chargehub.Level
import db.database.level.LevelDatabase
import db.database.level.LevelDatabaseWrapper
import db.networking.request.CreateLevelRequest
import db.repository.GenericRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.inject

class RemoteLevelRepository :
    GenericRepository<CreateLevelRequest, Level, LevelDatabase> {

    private val firestore = Firebase.firestore
    override val database: LevelDatabase
        get() = inject<LevelDatabaseWrapper>().value.database

    override suspend fun create(request: CreateLevelRequest) {
        firestore.collection(FIREBASE_LEVEL_COLLECTION).add(request)
        database.create(request)
    }
    override suspend fun fetchAll(): List<Level> {
        try {
            val levelResponse =
                firestore.collection(FIREBASE_LEVEL_COLLECTION).get()
            return levelResponse.documents.map { it.data() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchById(id: String): Level {
        try {
            val levelDocument =
                firestore.collection(FIREBASE_LEVEL_COLLECTION).document(id).get()
            return levelDocument.data()
        } catch(e: Exception) {
            throw e
        }
    }

    override suspend fun update(id: String, request: CreateLevelRequest) {
        firestore.collection(FIREBASE_LEVEL_COLLECTION).document(id).update(request)
        database.update(id, request)
    }

    override suspend fun delete(id: String) {
        firestore.collection(FIREBASE_LEVEL_COLLECTION).document(id).delete()
        database.delete(id)
    }

    override fun findById(id: String): Flow<Level> {
        return flowOf(database.getById(id))
    }

    override fun findAll(): Flow<List<Level>> {
        return flowOf(database.getAll())
    }
}