package db.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.component.KoinComponent

class FirebaseAuthentication : KoinComponent {
    private val auth = Firebase.auth

    suspend fun login(email: String, password: String): String {
        auth.signInWithEmailAndPassword(email, password)
        return auth.currentUser?.uid.toString()
    }

    suspend fun register(email: String, password: String): String {
        auth.createUserWithEmailAndPassword(email, password)
        return auth.currentUser?.uid.toString()
    }
}