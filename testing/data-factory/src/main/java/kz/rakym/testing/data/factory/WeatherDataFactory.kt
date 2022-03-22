package kz.rakym.testing.data.factory

import kz.rakym.domain.entity.*

object WeatherDataFactory {

    fun singleWeather(): WeatherData =
        WeatherData(
            coord = Coordinates(76.881, 43.201),
            weather = arrayListOf(
                Weather(
                    main = "Clouds",
                    description = "overcast clouds"
                )),
            main = WeatherParams(
                temp = 26.85f,
                feels_like = 26.85f,
                temp_min = 26.85f,
                temp_max = 26.85f,
                pressure = 1027,
                humidity = 81
            ),
            wind = Wind(
                speed = 2.8f,
                gust = 2.44f
            ),
            timezone = 21600,
            name = "Gornyy Gigant",
            temperatureSystem = null
        )

    fun singleDailyWeather(): DailyWeather =
        DailyWeather(daily = arrayListOf(
            Daily(
                dt = "1647842400",
                temp = DailyTemp(
                    day = 5.43f,
                    night = 4.86f
                ),
                humidity = 67,
                wind_speed = 2.13f,
                weather = arrayListOf(
                    DailyState(
                        main = "Rain",
                        description = "light rain"
                    )
                )
            )

        ))
}