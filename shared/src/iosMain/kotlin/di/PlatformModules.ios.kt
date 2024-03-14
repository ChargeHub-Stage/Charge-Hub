package di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import db.chargehub.ChargeHubDb
import db.database.user.UserDatabase
import db.database.user.UserDatabaseWrapper
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModules(): Module = module {
    single {
        val driver = NativeSqliteDriver(ChargeHubDb.Schema, "chargehubdb.db")
        UserDatabaseWrapper(UserDatabase(driver))
    }
}