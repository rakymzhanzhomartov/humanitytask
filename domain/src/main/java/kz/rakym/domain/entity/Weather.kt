package kz.rakym.domain.entity

data class Weather(
    val main: String,
    val description: String
) {

    fun getWeatherType(): WeatherType = WeatherType.getTypeByName(main)

    enum class WeatherType{
        Clear,
        Rain,
        Snow,
        Clouds,
        Fog;

        companion object {
            fun getTypeByName(value: String): WeatherType = WeatherType.values().firstOrNull { it.name.lowercase() == value.lowercase() } ?: Clear
        }
    }
}
