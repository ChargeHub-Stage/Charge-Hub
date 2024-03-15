package db.database

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.SqlDriver
import db.chargehub.ChargeHubDb


/**
 * Generic Database operations that each database class uses.
 * [T] represents the returning type of functions, usually the models.
 * [R] represents the "Request" type passed in parameters.
 */
abstract class GenericDatabaseOperations<T, R>(sqlDriver: SqlDriver) {

    internal val database = ChargeHubDb(sqlDriver)

    abstract fun getAll() : List<T>

    abstract fun getById(id: String) : T

    abstract fun create(request: R)

    abstract fun delete(id: String)

    abstract fun update(request: R)
}