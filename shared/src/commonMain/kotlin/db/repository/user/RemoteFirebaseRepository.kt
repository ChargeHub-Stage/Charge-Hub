package db.repository.user

import db.database.firebase.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.component.KoinComponent

class RemoteFirebaseRepository: FirebaseRepository, KoinComponent {
    private val firestore = Firebase.firestore

    override suspend fun getUsers(): List<User> {
        try {
            val userResponse =
                firestore.collection("USERS").get()
            return userResponse.documents.map { it.data() }
        } catch (e: Exception) {
            throw e
        }
    }
}