package com.example.notanotherweatherapp.model

data class ForecastResponse(
    val properties: ForecastProperties
)

data class ForecastProperties(
    val forecastHourly: String?
)

data class HourlyResponse(
    val properties: HourlyProperties
)

data class HourlyProperties(
    val periods: List<Period>
)

data class Period(
    val number: Int?,
    val name: String?,
    val startTime: String?,
    val endTime: String?,
    val isDayTime: Boolean?,
    val temperature: Int?,
    val probabilityOfPrecipitation: ProbabilityOfPrecipitation?,
    val dewpoint: Dewpoint,
    val relativeHumidity: RelativeHumidity?,
    val windSpeed: String?,
    val windDirection: String?,
    val icon: String?,
    val shortForecast: String?,
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