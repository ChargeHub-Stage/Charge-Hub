package db.repository.user

import db.chargehub.User
import db.database.UserDatabase
import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteUserRepository(private val httpClient: HttpClient) : UserRepository, KoinComponent {
    
    private val database: UserDatabase by inject()
    override fun create(user: User) {
        database.createUser(user)
    }

    override fun getAll(): List<User> {
        return database.getAllUsers()
    }


}