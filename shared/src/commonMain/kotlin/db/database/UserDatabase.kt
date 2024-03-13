package db.database

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.ChargeHubDb
import db.chargehub.User

internal class UserDatabase(sqlDriver: SqlDriver) {
    private val database = ChargeHubDb(sqlDriver)
    private val dbQuery = database.userDbQueries

    internal fun getAllUsers() : List<User> {
        return dbQuery.getAllUsers().executeAsList()
    }

    internal fun createUser(user: User) {
        dbQuery.insertUser(levelId = user.levelId, name = user.name, email = user.email, password = user.password, currentPoints = user.currentPoints)
    }

}