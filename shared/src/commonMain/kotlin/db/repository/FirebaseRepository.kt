package db.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.component.KoinComponent

class FirebaseRepository : KoinComponent {
    private val auth = Firebase.auth

    companion object {
        const val TEST_USER = "test_id"
    }


    suspend fun login(email: String, password: String, onSuccess: suspend () -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
        onSuccess()
    }

    suspend fun register(email: String, password: String, onSuccess: suspend () -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
        onSuccess()
    }

    fun getCurrentUserUid(): String = auth.currentUser?.uid ?: TEST_USER
}