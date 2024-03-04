package com.example.notanotherweatherapp.model

import com.example.notanotherweatherapp.R

enum class WeatherDisplay(
    val displayName: String,
    val iconId: Int,
    // TODO - Enum this or something idk
    val conditions: List<String>,
) {
    CLEAR_DAY(
        displayName = "Clear day",
        iconId = R.drawable.ic_clear_day,
        conditions = listOf(
            "sunny",
            "clear",
        ),
    ),
    CLEAR_NIGHT(
        displayName = "Clear night",
        iconId = R.drawable.ic_clear_night,
        conditions = listOf(
            "clear",
        ),
    ),
    PARTLY_CLOUDY_DAY(
        displayName = "Partly cloudy day",
        iconId = R.drawable.ic_partly_cloudy_day,
        conditions = listOf(
            "partly cloudy",
        ),
    ),
    PARTLY_CLOUDY_NIGHT(
        displayName = "Partly cloudy night",
        iconId = R.drawable.ic_partly_cloudy_night,
        conditions = listOf(
            "partly cloudy",
        ),
    ),
    CLOUDY(
        displayName = "Cloudy",
        iconId = R.drawable.ic_cloudy,
        conditions = listOf(
            "mostly cloudy",
            "cloudy",
        ),
    ),
    RAIN(
        displayName = "Rain",
        iconId = R.drawable.ic_rain,
        conditions = listOf(
            "rain",
            "showers",
        ),
    ),
    THUNDERSTORM(
        displayName = "Thunderstorm",
        iconId = R.drawable.ic_thunderstorm,
        conditions = listOf(
            "thunder",
            "storm",
        ),
    ),
    SNOW(
        displayName = "Snow",
        iconId = R.drawable.ic_snow,
        conditions = listOf(
            "snow",
        ),
    ),
}