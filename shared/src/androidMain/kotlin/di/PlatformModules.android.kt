package di


import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import db.chargehub.ChargeHubDb
import db.database.UserDatabase
import db.database.UserDatabaseWrapper
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModules() = module {
    single {
        val driver = AndroidSqliteDriver(ChargeHubDb.Schema, get(), "chargehubdb.db")
        UserDatabaseWrapper(UserDatabase(driver))
    }
}