package db.database.user

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.User
import db.chargehub.UserDbQueries
import db.database.GenericDatabaseOperations
import db.networking.request.CreateUserRequest
import db.networking.request.GetUserRequest

class UserDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<GetUserRequest, CreateUserRequest>(sqlDriver) {

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
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            currentPoints = request.currentPoints,
            carId = request.carId
        )
    }

    override fun create(request: CreateUserRequest) {
        query.insertUser(
            levelId = request.levelId,
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            currentPoints = request.currentPoints,
            carId = request.carId
        )
    }

    private fun User.mapToGetUserRequest() =
        GetUserRequest(
            id = id,
            levelId = levelId ?: "-1",
            carId = carId ?: "-1",
            firstName = firstName,
            lastName = lastName,
            email = email,
            currentPoints = currentPoints
        )
}