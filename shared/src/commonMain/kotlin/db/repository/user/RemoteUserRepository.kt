package db.repository.user

import db.chargehub.User
import db.database.user.UserDatabaseWrapper
import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteUserRepository(private val httpClient: HttpClient) : UserRepository, KoinComponent {
    
    private val databaseWrapper: UserDatabaseWrapper by inject()
    override fun create(user: User) {
        databaseWrapper.database?.createUser(user)
    }

    override fun getAll(): List<User> {
        return databaseWrapper.database?.getAllUsers() ?: listOf()
    }


}