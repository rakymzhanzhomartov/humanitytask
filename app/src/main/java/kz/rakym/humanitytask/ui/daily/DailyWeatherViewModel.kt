package kz.rakym.humanitytask.ui.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.rakym.domain.interaction.GetWeatherCase

class DailyWeatherViewModel(private val weatherUseCase: GetWeatherCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}