package db.database.user

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.User
import db.chargehub.UserDbQueries
import db.database.GenericDatabaseOperations
import db.networking.request.user.CreateUserRequest
import db.networking.request.user.GetUserRequest
import db.networking.request.user.UserRequest

class UserDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<UserRequest, CreateUserRequest>(sqlDriver) {

    private val query: UserDbQueries
        get() = database.userDbQueries

    override fun getAll(): List<GetUserRequest> {
        return query.getAllUsers().executeAsList().map { it.mapToGetUserRequest() }
    }

    override fun getById(id: String): GetUserRequest {
        return query.getUserById(id).executeAsOne().mapToGetUserRequest()
    }

    override fun delete(id: String) {
        query.deleteUser(id)
    }

    override fun update(id: String, request: CreateUserRequest) {
        query.updateUser(
            id = id,
            levelId = request.levelId,
            name = request.name,
            email = request.email,
            password = request.password,
            currentPoints = request.currentPoints
        )
    }

    override fun create(request: CreateUserRequest) {
        query.insertUser(
            levelId = request.levelId,
            name = request.name,
            email = request.email,
            password = request.password,
            currentPoints = request.currentPoints
        )
    }

    private fun User.mapToGetUserRequest() : GetUserRequest = GetUserRequest(name = name, email = email, password = password)
}