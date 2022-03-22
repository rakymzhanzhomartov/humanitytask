package kz.rakym.data.datasource

import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.DailyWeather
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.entity.WeatherData

interface RemoteWeatherDataSource {
    suspend fun getCurrentWeatherForLocation(
        coordinates: Coordinates,
        temperatureSystem: TemperatureSystem,
        appId: String
    ): WeatherData

    suspend fun getDailyWeather(
        coordinates: Coordinates,
        temperatureSystem: TemperatureSystem,
        appId: String
    ): DailyWeather
}