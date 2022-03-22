package kz.rakym.humanitytask.di

import kotlinx.coroutines.Dispatchers
import kz.rakym.domain.platform.CoroutineScope
import org.koin.dsl.module

val coroutineScopeModule = module {

    single<CoroutineScope> {
        // To make the testing process easily
        return@single object : CoroutineScope {
            override fun io() = Dispatchers.IO
            override fun main() = Dispatchers.Main
            override fun default() = Dispatchers.Default
            override fun unconfined() = Dispatchers.Unconfined
        }
    }
}