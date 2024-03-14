package db.database.user

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.ChargeHubDb
import db.chargehub.User
import db.networking.request.CreateUserRequest

class UserDatabase(sqlDriver: SqlDriver) {
    private val database = ChargeHubDb(sqlDriver)
    private val dbQuery = database.userDbQueries

    fun getAll(): List<User> {
        return dbQuery.getAllUsers().executeAsList()
    }

    fun fetchById(id: Long): User {
        return dbQuery.getUserById(id).executeAsOne()
    }

    fun delete(id: Long) {
        dbQuery.deleteUser(id)
    }

    fun update(id: Long, updatedUser: CreateUserRequest) {
        dbQuery.updateUser(
            id = id,
            levelId = updatedUser.levelId,
            name = updatedUser.name,
            email = updatedUser.email,
            password = updatedUser.password,
            currentPoints = updatedUser.currentPoints
        )
    }

    fun createUser(user: CreateUserRequest) {
        dbQuery.insertUser(
            levelId = user.levelId,
            name = user.name,
            email = user.email,
            password = user.password,
            currentPoints = user.currentPoints
        )
    }
}