package kz.rakym.domain.entity

data class WeatherData(
    val coord: Coordinates,
    val weather: ArrayList<Weather>,
    val main: WeatherParams,
    val wind: Wind,
    val timezone: Int,
    val name: String,
    val temperatureSystem: TemperatureSystem? = TemperatureSystem.DEFAULT
)
