package kz.rakym.humanitytask.ui.home

import kz.rakym.domain.entity.WeatherData
import kz.rakym.humanitytask.ui.base.BaseViewState

sealed class HomeViewState: BaseViewState() {

    class Success(val data: WeatherData): HomeViewState()
}