package db.repository.user

import db.database.firebase.User

interface FirebaseRepository {
    suspend fun getUsers(): List<User>
}