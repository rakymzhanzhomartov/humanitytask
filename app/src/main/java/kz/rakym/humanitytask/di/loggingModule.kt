package kz.rakym.humanitytask.di


import kz.rakym.library.logger.Logger
import kz.rakym.logger.android.LogcatLogger
import org.koin.dsl.module

val loggingModule = module {
    single<Logger> { LogcatLogger }
}