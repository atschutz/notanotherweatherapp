package com.example.notanotherweatherapp

import com.example.notanotherweatherapp.model.Dewpoint
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.model.ProbabilityOfPrecipitation
import com.example.notanotherweatherapp.model.RelativeHumidity

val TEST_PROBABILITY_OF_PRECIPITATION =
    ProbabilityOfPrecipitation("wmoUnit:percent", 30.0)

val TEST_DEWPOINT =
    Dewpoint("wmoUnit:degC", -9.444444444444445)

val TEST_RELATIVE_HUMIDITY =
    RelativeHumidity("wmoUnit:percent", 62.0)

val TEST_PERIOD = Period(
    number = 1,
    name = "",
    startTime = "2024-02-17T22:00:00-06:00",
    endTime = "2024-02-17T23:00:00-06:00",
    isDayTime = true,
    temperature = 99,
    probabilityOfPrecipitation = TEST_PROBABILITY_OF_PRECIPITATION,
    dewpoint = TEST_DEWPOINT,
    relativeHumidity = TEST_RELATIVE_HUMIDITY,
    windSpeed = "10 mph",
    windDirection = "SW",
    icon = "https://api.weather.gov/icons/land/night/skc,0?size=small",
    shortForecast = "Clear",
)

val TEST_CLOTHING_MAP = mapOf(
    "Gloves" to R.drawable.ic_sunny,
    "Boots" to R.drawable.ic_sunny,
    "Short sleeve shirt" to R.drawable.ic_sunny,
    "Long Sleeve shirt" to R.drawable.ic_sunny,
    "Shorts" to R.drawable.ic_sunny,
    "Pants" to R.drawable.ic_sunny,
    "Jacket" to R.drawable.ic_sunny,
    "Umbrella" to R.drawable.ic_sunny,
)