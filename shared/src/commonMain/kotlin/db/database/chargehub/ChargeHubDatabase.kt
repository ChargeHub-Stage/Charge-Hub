package db.database.chargehub

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.ChargeHub
import db.database.GenericDatabaseOperations
import db.networking.request.CreateChargeHubRequest

class ChargeHubDatabase(sqlDriver: SqlDriver) :
    GenericDatabaseOperations<ChargeHub, CreateChargeHubRequest>(sqlDriver) {

    private val query
        get() = database.chargeHubDbQueries

    override fun getAll(): List<ChargeHub> {
        return query.getAllChargeHubs().executeAsList()
    }

    override fun getById(id: String): ChargeHub {
        return query.getChargeHubById(id).executeAsOne()
    }

    override fun delete(id: String) {
        query.deleteChargeHub(id)
    }

    override fun update(request: CreateChargeHubRequest) {
        request.id?.let { query.updateChargeHub(id = it, name = request.name) }
    }

    override fun create(request: CreateChargeHubRequest) {
        query.insertChargeHub(name = request.name)
    }
}