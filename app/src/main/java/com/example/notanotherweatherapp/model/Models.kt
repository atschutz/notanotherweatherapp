package com.example.notanotherweatherapp.model

data class LocationResponse(
    val properties: LocationProperties
)

data class LocationProperties(
    val forecast: String = "",
    val forecastHourly: String = "",
)

data class ForecastResponse(
    val properties: ForecastProperties
)

data class ForecastProperties(
    val periods: List<Period>
)

data class Period(
    val number: Int = 0,
    val name: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val isDaytime: Boolean = true,
    val temperature: Int = 0,
    val probabilityOfPrecipitation: ProbabilityOfPrecipitation =
        ProbabilityOfPrecipitation("", 0.0),
    val dewpoint: Dewpoint =
        Dewpoint("", 0.0),
    val relativeHumidity: RelativeHumidity =
        RelativeHumidity("", 0.0),
    val windSpeed: String = "",
    val windDirection: String = "",
    val icon: String = "",
    val shortForecast: String = "",
    val detailedForecast: String = "",
)

data class ProbabilityOfPrecipitation(
    val unitCode: String,
    val value: Double,
)

data class Dewpoint(
    val unitCode: String,
    val value: Double,
)

data class RelativeHumidity(
    val unitCode: String,
    val value: Double,
)

data class PeriodGroup(
    val period: Period,
    val weatherCondition: WeatherCondition,
    val clothing: Clothing
)

data class ClothingChange(
    val clothing: Clothing,
    val time: String,
    val temperature: Int,
)