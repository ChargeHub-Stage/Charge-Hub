package db.database.networkcalls

import app.cash.sqldelight.db.SqlDriver
import db.chargehub.ChargeHubDb
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * Database for storing the last time a network call.
 * To the car connect API has been made.
 * Calls can be made once every 5 minutes per user.
 */
class CarConnectNetworkCallsDatabase(sqlDriver: SqlDriver) {

    private val database = ChargeHubDb(sqlDriver)

    private val query
        get() = database.carConnectNetworkCallsDbQueries

    //TODO actual network call
    fun createOrInsertNetworkCall() {
        if (canSendNetworkRequest()) {
            query.insertOrUpdateNetworkCall(
                Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString()
            )
        }
    }

    private fun getLastNetworkCall(): LocalDateTime? {
        val queryRes = query.getLastNetworkCall().executeAsOneOrNull() ?: return null
        return LocalDateTime.parse(queryRes)
    }

    private fun canSendNetworkRequest(): Boolean {
        val now = Clock.System.now().toEpochMilliseconds()
        // If no last call is made (first time ever checking this, return true)
        val lastCall =
            getLastNetworkCall()?.toInstant(TimeZone.currentSystemDefault())?.toEpochMilliseconds()
                ?: return true
        return lastCall + 300_000L <= now
    }
}
