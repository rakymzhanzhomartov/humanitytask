package kz.rakym.humanitytask.ui

object WeatherParameters {
    const val latitude = 43.139417
    const val longitude = 77.010239
    const val appId = "6d918d10b9f1873bd6e1d2173fc328bf"

    //This parameter is used to exclude excessive data from daily request to API
    const val dailyWeatherExcludeParameters = "current,minutely,hourly,alerts"
}