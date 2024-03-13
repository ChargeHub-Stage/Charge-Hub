package di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import db.chargehub.ChargeHubDb
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModules(): Module = module {
   single { NativeSqliteDriver(ChargeHubDb.Schema, "chargehubdb.db") }
}