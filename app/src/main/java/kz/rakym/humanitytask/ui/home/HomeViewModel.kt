package kz.rakym.humanitytask.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.rakym.domain.Result
import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.interaction.GetWeatherCase
import kz.rakym.humanitytask.ui.WeatherParameters
import kz.rakym.humanitytask.ui.base.BaseViewState
import kz.rakym.humanitytask.ui.utils.io
import kz.rakym.humanitytask.ui.utils.ui

class HomeViewModel(private val getWeatherCase: GetWeatherCase) : ViewModel() {

    private val _state = MutableLiveData<BaseViewState>().apply {
        value = BaseViewState.Loading
    }
    val state: LiveData<BaseViewState> = _state
    var temperatureSystem: TemperatureSystem = TemperatureSystem.CELSIUS
        set(value) {
            if (value != field){
                field = value
                loadWeatherData()
            }
        }

    init {
        loadWeatherData()
    }

    fun refresh(){
        loadWeatherData()
    }

    private fun loadWeatherData() {
        viewModelScope.launch {
            _state.value = BaseViewState.Loading
            io {
                //This delay is used to show animation of RotatedProgressView
                delay(5000)
                val result = getWeatherCase.execute(
                    temperatureSystem,
                    Coordinates(
                        WeatherParameters.longitude,
                        WeatherParameters.latitude
                    ),
                    WeatherParameters.appId
                )
                ui {
                    when (result) {
                        is Result.Success -> {
                            _state.value = HomeViewState.Success(result.result)
                        }
                        is Result.Failure -> {
                            _state.value = BaseViewState.Error(result.throwable.localizedMessage)
                        }
                    }
                }
            }

        }
    }


}