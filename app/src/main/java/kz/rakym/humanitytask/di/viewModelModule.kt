package kz.rakym.humanitytask.di

import kz.rakym.humanitytask.ui.daily.DailyWeatherViewModel
import kz.rakym.humanitytask.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel{DailyWeatherViewModel(get())}

    viewModel{HomeViewModel(get())}
}