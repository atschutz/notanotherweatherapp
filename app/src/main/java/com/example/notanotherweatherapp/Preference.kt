package com.example.notanotherweatherapp

enum class Preference(
    val key: String,
    val displayName: String,
    val defaultValue: String,
    val preferenceType: PreferenceType,
    val toggleChoices: Pair<ToggleOption, ToggleOption>? = null
) {
    COMFY_TEMPERATURE(
        key = "comfy_temperature",
        displayName = "Comfy temperature",
        defaultValue = "73",
        preferenceType = PreferenceType.INPUT,
    ),
    DISPLAY_HOURS(
        key = "display_hours",
        displayName = "Hours to plan for",
        defaultValue = "24",
        preferenceType = PreferenceType.INPUT,
    ),
    TEMPERATURE_UNIT(
        key = "temperature_unit",
        displayName = "Temperature unit",
        defaultValue = ToggleOption.FAHRENHEIT.key,
        preferenceType = PreferenceType.TOGGLE,
        toggleChoices = Pair(
            ToggleOption.FAHRENHEIT,
            ToggleOption.CELSIUS
        )
    )
}

enum class ToggleOption(
    val key: String,
    val displayName: String,
) {
    FAHRENHEIT(
        key = "fahrenheit",
        displayName = "F"
    ),
    CELSIUS(
        key = "celsius",
        displayName = "C",
    )
}

enum class PreferenceType {
    INPUT,
    TOGGLE,
}