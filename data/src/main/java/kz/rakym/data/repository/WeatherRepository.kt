package kz.rakym.data.repository

import kz.rakym.data.datasource.RemoteWeatherDataSource
import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.DailyWeather
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.entity.WeatherData
import kz.rakym.domain.service.WeatherService
import kz.rakym.library.logger.Logger

class WeatherRepository(
    private val logger: Logger,
    private val remoteWeatherDataSource: RemoteWeatherDataSource
): WeatherService {

    override suspend fun getWeatherData(
        temperatureSystem: TemperatureSystem,
        coordinates: Coordinates,
        appId: String
    ): WeatherData {
        return remoteWeatherDataSource.getCurrentWeatherForLocation(coordinates, temperatureSystem, appId)
    }

    override suspend fun getDailyData(
        temperatureSystem: TemperatureSystem,
        coordinates: Coordinates,
        appId: String
    ): DailyWeather {
        return remoteWeatherDataSource.getDailyWeather(coordinates, temperatureSystem, appId)
    }
}