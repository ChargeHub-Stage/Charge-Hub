package di

import DATABASE_NAME
import ResourceStrings
import app.cash.sqldelight.driver.native.NativeSqliteDriver
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
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModules(): Module = module {
    val driver = NativeSqliteDriver(ChargeHubDb.Schema, DATABASE_NAME)
    single {
        UserDatabaseWrapper(UserDatabase(driver))
    }

    single {
        CarDatabaseWrapper(CarDatabase(driver))
    }

    single {
        ChargeHubDatabaseWrapper(ChargeHubDatabase(driver))
    }

    single {
        LevelDatabaseWrapper(LevelDatabase(driver))
    }

    single {
        ReservationDatabaseWrapper(ReservationDatabase(driver))
    }

    single {
        CarConnectNetworkCallsWrapper(CarConnectNetworkCallsDatabase((driver)))
    }

    single {
        ResourceStrings()
    }
}