package kz.rakym.humanitytask.di

import kz.rakym.data.datasource.RemoteWeatherDataSource
import kz.rakym.data.datasource.RemoteWeatherDataSourceImpl
import kz.rakym.data.repository.WeatherRepository
import kz.rakym.domain.service.WeatherService
import org.koin.dsl.module

val weatherDataModule = module {
    single<RemoteWeatherDataSource> {
        RemoteWeatherDataSourceImpl(
            api = get()
        )
    }

    single<WeatherService> {
        WeatherRepository(
            logger = get(),
            remoteWeatherDataSource = get()
        )
    }
}