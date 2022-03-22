package kz.rakym.domain.entity

data class Daily(
        val dt: String,
        val temp: DailyTemp,
        val humidity: Int,
        val wind_speed: Float,
        val weather: ArrayList<DailyState>
)
