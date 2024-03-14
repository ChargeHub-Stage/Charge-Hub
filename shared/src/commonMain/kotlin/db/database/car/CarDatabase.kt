package db.database.car

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.ChargeHubDb
import db.chargehub.User

class CarDatabase(sqlDriver: SqlDriver) {

    private val database = ChargeHubDb(sqlDriver)
    private val dbQuery = database.userDbQueries



}