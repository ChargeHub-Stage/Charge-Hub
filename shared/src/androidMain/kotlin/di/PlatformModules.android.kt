package di


import android.util.Log
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import db.chargehub.ChargeHubDb
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModules(): Module = module {
    Log.d("TAGG", "platformModules: registered")
    single { AndroidSqliteDriver(ChargeHubDb.Schema, get(), "chargehubdb.db") }
}