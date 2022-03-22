package kz.rakym.domain.service

import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.DailyWeather
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.entity.WeatherData

interface WeatherService {
    suspend fun getWeatherData(
        temperatureSystem: TemperatureSystem,
        coordinates: Coordinates,
        appId: String
    ): WeatherData

    suspend fun getDailyData(
        temperatureSystem: TemperatureSystem,
        coordinates: Coordinates,
        appId: String
    ): DailyWeather
}