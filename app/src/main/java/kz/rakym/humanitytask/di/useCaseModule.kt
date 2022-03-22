package kz.rakym.humanitytask.di

import kz.rakym.domain.interaction.GetDailyCase
import kz.rakym.domain.interaction.GetWeatherCase
import org.koin.dsl.module

val weatherUseCaseModule = module {
    single { GetWeatherCase(service = get(), logger = get()) }
    single { GetDailyCase(service = get(), logger = get()) }
}