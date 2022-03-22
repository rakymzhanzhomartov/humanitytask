package kz.rakym.data.datasource

import kz.rakym.data.api.WeatherApi
import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.DailyWeather
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.entity.WeatherData

class RemoteWeatherDataSourceImpl(private val api: WeatherApi) : RemoteWeatherDataSource {

    override suspend fun getCurrentWeatherForLocation(
        coordinates: Coordinates,
        temperatureSystem: TemperatureSystem,
        appId: String
    ): WeatherData {
        return api.getCurrentWeatherForLocation(
            coordinates.lat,
            coordinates.lon,
            appId,
            temperatureSystem.serverValue
        )
    }

    override suspend fun getDailyWeather(
        coordinates: Coordinates,
        temperatureSystem: TemperatureSystem,
        appId: String
    ): DailyWeather {
        return api.getDailyWeather(
            coordinates.lat,
            coordinates.lon,
            appId,
            temperatureSystem.serverValue
        )
    }

}