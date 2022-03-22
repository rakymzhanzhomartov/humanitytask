package kz.rakym.domain.interaction


import kz.rakym.domain.Result
import kz.rakym.domain.entity.Coordinates
import kz.rakym.domain.entity.DailyWeather
import kz.rakym.domain.entity.TemperatureSystem
import kz.rakym.domain.service.WeatherService
import kz.rakym.library.logger.Logger

class GetDailyCase(
    private val service: WeatherService,
    private val logger: Logger
) {
    suspend fun execute(
        temperatureSystem: TemperatureSystem,
        coordinates: Coordinates,
        appId: String
    ): Result<DailyWeather> {
        return try {
            Result.Success(service.getDailyData(temperatureSystem, coordinates, appId))
        } catch (ex: Exception) {
            logger.e(e = ex)
            Result.Failure(ex)
        }
    }
}