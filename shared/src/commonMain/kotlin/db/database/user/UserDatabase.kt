package db.database.user

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.ChargeHubDb
import db.chargehub.User

class UserDatabase(sqlDriver: SqlDriver) {
    private val database = ChargeHubDb(sqlDriver)
    private val dbQuery = database.userDbQueries

    fun getAllUsers(): List<User> {
        return dbQuery.getAllUsers().executeAsList()
    }

    fun createUser(user: User) {
        dbQuery.insertUser(
            levelId = user.levelId,
            name = user.name,
            email = user.email,
            password = user.password,
            currentPoints = user.currentPoints
        )
    }

}