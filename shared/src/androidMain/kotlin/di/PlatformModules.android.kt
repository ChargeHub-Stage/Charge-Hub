package di


import DATABASE_NAME
import ResourceStrings
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import db.chargehub.ChargeHubDb
import db.database.car.CarDatabase
import db.database.car.CarDatabaseWrapper
import db.database.chargehub.ChargeHubDatabase
import db.database.chargehub.ChargeHubDatabaseWrapper
import db.database.level.LevelDatabase
import db.database.level.LevelDatabaseWrapper
import db.database.networkcalls.CarConnectNetworkCallsDatabase
import db.database.networkcalls.CarConnectNetworkCallsWrapper
import db.database.reservation.ReservationDatabase
import db.database.reservation.ReservationDatabaseWrapper
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import org.koin.dsl.module

actual fun platformModules() = module {

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), DATABASE_NAME)
        UserDatabaseWrapper(UserDatabase(driver))
    }

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), DATABASE_NAME)
        CarDatabaseWrapper(CarDatabase(driver))
    }

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), DATABASE_NAME)
        ChargeHubDatabaseWrapper(ChargeHubDatabase(driver))
    }

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), DATABASE_NAME)
        LevelDatabaseWrapper(LevelDatabase(driver))
    }

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), DATABASE_NAME)
        ReservationDatabaseWrapper(ReservationDatabase(driver))
    }

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), DATABASE_NAME)
        CarConnectNetworkCallsWrapper(CarConnectNetworkCallsDatabase((driver)))
    }

    single { ResourceStrings(get()) }
}