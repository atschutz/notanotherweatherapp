package com.example.notanotherweatherapp

import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ClothingChange
import com.example.notanotherweatherapp.model.Dewpoint
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.model.ProbabilityOfPrecipitation
import com.example.notanotherweatherapp.model.RelativeHumidity
import com.example.notanotherweatherapp.model.WeatherCondition

val TEST_PROBABILITY_OF_PRECIPITATION =
    ProbabilityOfPrecipitation("wmoUnit:percent", 30.0)

val TEST_DEWPOINT =
    Dewpoint("wmoUnit:degC", -9.444444444444445)

val TEST_RELATIVE_HUMIDITY =
    RelativeHumidity("wmoUnit:percent", 62.0)

val TEST_PERIOD = Period(
    number = 1,
    name = "Wednesday Night",
    startTime = "2024-02-17T22:00:00-06:00",
    endTime = "2024-02-17T23:00:00-06:00",
    isDaytime = true,
    temperature = 99,
    probabilityOfPrecipitation = TEST_PROBABILITY_OF_PRECIPITATION,
    dewpoint = TEST_DEWPOINT,
    relativeHumidity = TEST_RELATIVE_HUMIDITY,
    windSpeed = "10 mph",
    windDirection = "SW",
    icon = "https://api.weather.gov/icons/land/night/skc,0?size=small",
    shortForecast = "Clear",
    detailedForecast = "A chance of rain showers. Mostly cloudy, with a high near 48. Chance of precipitation is 50%."
)

val TEST_HOURLY_GROUP = PeriodGroup(
    period = TEST_PERIOD,
    clothing = Clothing.TEE_SHIRT,
    weatherCondition = WeatherCondition.CLEAR_DAY,
)

val TEST_CLOTHING_CHANGE =
    ClothingChange(
        clothing = Clothing.SCARF,
        time = "8pm",
        temperature = 76,
    )

val TEST_CLOTHING_CHANGES = listOf(
    TEST_CLOTHING_CHANGE,
    TEST_CLOTHING_CHANGE,
    TEST_CLOTHING_CHANGE,
    TEST_CLOTHING_CHANGE,
    TEST_CLOTHING_CHANGE,
)