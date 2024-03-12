package di

import Greeting
import org.koin.dsl.module


val modules = module {
    single { Greeting() }
}