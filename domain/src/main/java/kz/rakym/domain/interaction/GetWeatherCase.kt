package kz.rakym.domain.interaction

import kz.rakym.domain.entity.WeatherData
import kz.rakym.domain.Result
import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.service.WeatherService
import kz.rakym.library.logger.Logger

class GetWeatherCase(
    private val service: WeatherService,
    private val logger: Logger
) {
    suspend fun execute(temperatureSystem: TemperatureSystem, coordinates: Coordinates, appId: String): Result<WeatherData>{
        return try {
            Result.Success(service.getWeatherData(temperatureSystem, coordinates, appId))
        } catch (ex: Exception){
            logger.e(e = ex)
            Result.Failure(ex)
        }
    }
}