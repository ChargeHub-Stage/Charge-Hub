package di


import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import db.chargehub.ChargeHubDb
import db.database.car.CarDatabase
import db.database.car.CarDatabaseWrapper
import db.database.chargehub.ChargeHubDatabase
import db.database.chargehub.ChargeHubDatabaseWrapper
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import org.koin.dsl.module

actual fun platformModules() = module {

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), "chargehubdb.db")
        UserDatabaseWrapper(UserDatabase(driver))
    }

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), "chargehubdb.db")
        CarDatabaseWrapper(CarDatabase(driver))
    }

    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), "chargehubdb.db")
        ChargeHubDatabaseWrapper(ChargeHubDatabase(driver))
    }
}