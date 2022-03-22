package kz.rakym.domain.entity

enum class TemperatureSystem(val serverValue: String, val symbol: String) {
    CELSIUS("metric", "°C"),
    FAHRENHEIT("imperial", "°F"),
    DEFAULT("standard", "K")


}