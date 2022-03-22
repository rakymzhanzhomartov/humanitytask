package kz.rakym.humanitytask

import android.app.Application
import kz.rakym.humanitytask.di.*
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidLogger(Level.ERROR)
            //androidContext(this@App)
            modules(
                listOf(
                    loggingModule,
                    coroutineScopeModule,

                    weatherDataModule,
                    weatherNetworkModule,
                    weatherUseCaseModule,
                    viewModelModule
                )
            )
        }
    }
}