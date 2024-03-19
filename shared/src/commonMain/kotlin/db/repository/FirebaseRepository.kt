package db.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.component.KoinComponent

class FirebaseRepository : KoinComponent {
    private val auth = Firebase.auth

    suspend fun login(email: String, password: String, onSuccess: () -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
        onSuccess()
    }

    suspend fun register(email: String, password: String, onSuccess: () -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
        onSuccess()
    }

    fun getCurrentUserUid(): String = auth.currentUser?.uid.toString()
}