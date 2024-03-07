package com.example.notanotherweatherapp.model

import com.example.notanotherweatherapp.R

enum class WeatherCondition(
    val displayName: String,
    val iconId: Int,
    val isForDay: Boolean?,
    val conditionType: ConditionType,
    // TODO - Enum this or something idk
    val conditionKeywords: List<String>,
) {
    CLEAR_DAY(
        displayName = "Clear day",
        iconId = R.drawable.ic_clear_day,
        isForDay = true,
        conditionType = ConditionType.GENERAL,
        conditionKeywords = listOf(
            "sunny",
            "clear",
        ),
    ),
    CLEAR_NIGHT(
        displayName = "Clear night",
        iconId = R.drawable.ic_clear_night,
        isForDay = false,
        conditionType = ConditionType.GENERAL,
        conditionKeywords = listOf(
            "clear",
        ),
    ),
    PARTLY_CLOUDY_DAY(
        displayName = "Partly cloudy day",
        iconId = R.drawable.ic_partly_cloudy_day,
        isForDay = true,
        conditionType = ConditionType.GENERAL,
        conditionKeywords = listOf(
            "partly cloudy",
        ),
    ),
    PARTLY_CLOUDY_NIGHT(
        displayName = "Partly cloudy night",
        iconId = R.drawable.ic_partly_cloudy_night,
        isForDay = false,
        conditionType = ConditionType.GENERAL,
        conditionKeywords = listOf(
            "partly cloudy",
        ),
    ),
    CLOUDY(
        displayName = "Cloudy",
        iconId = R.drawable.ic_cloudy,
        isForDay = null,
        conditionType = ConditionType.GENERAL,
        conditionKeywords = listOf(
            "mostly cloudy",
            "cloudy",
        ),
    ),
    RAIN(
        displayName = "Rain",
        iconId = R.drawable.ic_rain,
        isForDay = null,
        conditionType = ConditionType.PRECIPITATION,
        conditionKeywords = listOf(
            "rain",
            "showers",
        ),
    ),
    THUNDERSTORM(
        displayName = "Thunderstorm",
        iconId = R.drawable.ic_thunderstorm,
        isForDay = null,
        conditionType = ConditionType.PRECIPITATION,
        conditionKeywords = listOf(
            "thunder",
            "storm",
        ),
    ),
    SNOW(
        displayName = "Snow",
        iconId = R.drawable.ic_snow,
        isForDay = null,
        conditionType = ConditionType.PRECIPITATION,
        conditionKeywords = listOf(
            "snow",
        ),
    ),
    WIND(
        displayName = "Wind",
        iconId = R.drawable.ic_wind,
        isForDay = null,
        conditionType = ConditionType.ADVISORY,
        conditionKeywords = listOf(
            "wind",
            "windy",
            "gust",
        ),
    ),
}

enum class ConditionType {
    PRECIPITATION,
    ADVISORY,
    GENERAL,
}