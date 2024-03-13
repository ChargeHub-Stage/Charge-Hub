package di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformModules(): Module = module {
   // single { NativeSqliteDriver(.Schema, "chargehubdb.db") }
}