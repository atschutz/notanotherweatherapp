package com.example.notanotherweatherapp

enum class Preference(
    val key: String,
    val displayName: String,
    val defaultValue: String,
) {
    COMFY_TEMPERATURE(
        key = "comfy_temperature",
        displayName = "Comfortable temperature",
        defaultValue = "73",
    ),
    DISPLAY_HOURS(
        key = "display_hours",
        displayName = "Hours to plan for",
        defaultValue = "24",
    ),
    TEMPERATURE_UNIT(
        key = "temperature_unit",
        displayName = "Temperature Unit",
        defaultValue = Unit.FAHRENHEIT.key,
    )
}

enum class Unit(
    val key: String
) {
    FAHRENHEIT(key = "F"),
    CELSIUS(key = "C"),
}