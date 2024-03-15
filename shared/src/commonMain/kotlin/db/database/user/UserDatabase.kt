package db.database.user

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.User
import db.chargehub.UserDbQueries
import db.database.GenericDatabaseOperations
import db.networking.request.UserRequest

class UserDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<UserRequest, UserRequest>(sqlDriver) {

    private val query: UserDbQueries
        get() = database.userDbQueries

    override fun getAll(): List<UserRequest> {
        return query.getAllUsers().executeAsList().map { it.mapToUserRequest() }
    }

    override fun getById(id: String): UserRequest {
        return query.getUserById(id).executeAsOne().mapToUserRequest()
    }

    override fun delete(id: String) {
        query.deleteUser(id)
    }

    override fun update(request: UserRequest) {
        request.id?.let {
            query.updateUser(
                id = it,
                levelId = request.levelId,
                name = request.name,
                email = request.email,
                password = request.password,
                currentPoints = request.currentPoints
            )
        }
    }

    override fun create(request: UserRequest) {
        query.insertUser(
            levelId = request.levelId,
            name = request.name,
            email = request.email,
            password = request.password,
            currentPoints = request.currentPoints
        )
    }

    private fun User.mapToUserRequest(): UserRequest {
        return UserRequest(
            id = id,
            levelId = levelId ?: "",
            name = name,
            email = email,
            password = password,
            currentPoints = currentPoints
        )
    }
}