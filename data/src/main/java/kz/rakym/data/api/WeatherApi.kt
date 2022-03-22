package kz.rakym.data.api

import kz.rakym.domain.entity.DailyWeather
import kz.rakym.domain.entity.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("2.5/weather")
    suspend fun getCurrentWeatherForLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String
    ) : WeatherData

    @GET("2.5/onecall")
    suspend fun getDailyWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String
    ) : DailyWeather
}