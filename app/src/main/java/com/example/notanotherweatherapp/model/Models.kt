package com.example.notanotherweatherapp.model

data class LocationResponse(
    val properties: LocationProperties
)

data class LocationProperties(
    val forecast: String?,
    val forecastHourly: String?,
)

data class ForecastResponse(
    val properties: ForecastProperties
)

data class ForecastProperties(
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
    val detailedForecast: String?,
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

data class HourlyGroup(
    val period: Period,
    val weatherDisplay: WeatherDisplay?,
    val clothing: Clothing?,
)