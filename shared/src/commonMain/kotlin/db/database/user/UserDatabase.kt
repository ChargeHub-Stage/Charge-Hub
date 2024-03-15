package db.database.user

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.User
import db.chargehub.UserDbQueries
import db.database.GenericDatabaseOperations
import db.networking.request.UserRequest

class UserDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<User, UserRequest>(sqlDriver) {

    private val query: UserDbQueries
        get() = database.userDbQueries

    override fun getAll(): List<User> {
        return query.getAllUsers().executeAsList()
    }

    override fun getById(id: String): User {
        return query.getUserById(id).executeAsOne()
    }

    override fun delete(id: String) {
        query.deleteUser(id)
    }

    override fun update(id: String, request: UserRequest) {
        query.updateUser(
            id = id,
            levelId = request.levelId,
            name = request.name,
            email = request.email,
            password = request.password,
            currentPoints = request.currentPoints
        )
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
}