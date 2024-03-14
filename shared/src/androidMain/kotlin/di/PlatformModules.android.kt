package di


import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import db.chargehub.ChargeHubDb
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import org.koin.dsl.module

actual fun platformModules() = module {
    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), "chargehubdb.db")
        UserDatabaseWrapper(UserDatabase(driver))
    }
}