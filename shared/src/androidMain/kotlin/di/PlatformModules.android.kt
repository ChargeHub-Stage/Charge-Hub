package di


import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModules(): Module = module {
  //  single { AndroidSqliteDriver(ChargeHubDb.Schema, get(), "chargehubdb.db") }
}