package com.example.notanotherweatherapp.model

import com.example.notanotherweatherapp.R

enum class Clothing(
    val displayName: String,
    val iconId: Int,
    val temperatureRange: IntRange,
    val isAccessory: Boolean,
    val conditions: List<Condition>,
    val conditionDescription: String,
) {
    TANK_TOP(
        displayName = "tank top",
        iconId = R.drawable.ic_tank_top,
        temperatureRange = 85..Int.MAX_VALUE,
        isAccessory = false,
        conditions = listOf(),
        conditionDescription = "It's hot out! Wear a tank top.",
    ),
    TEE_SHIRT(
        displayName = "tee shirt",
        iconId = R.drawable.ic_tee_shirt,
        temperatureRange = 70..84,
        isAccessory = false,
        conditions = listOf(),
        conditionDescription = "It's warm. Wear a tee shirt."
    ),
    SWEATSHIRT(
        displayName = "sweatshirt",
        iconId = R.drawable.ic_sweatshirt,
        temperatureRange = 55..69,
        isAccessory = false,
        conditions = listOf(
            Condition.WINDY,
        ),
        conditionDescription = "It's a little chilly. Put on a sweatshirt."
    ),
    LIGHT_JACKET(
        displayName = "light jacket",
        iconId = R.drawable.ic_light_jacket,
        temperatureRange = 40..54,
        isAccessory = false,
        conditions = listOf(
            Condition.WINDY,
            Condition.RAINING,
        ),
        conditionDescription = "It's pretty cold. Put on a light jacket"
    ),
    HEAVY_JACKET(
        displayName = "heavy jacket",
        iconId = R.drawable.ic_heavy_jacket,
        temperatureRange = Int.MIN_VALUE..39,
        isAccessory = false,
        conditions = listOf(
            Condition.WINDY,
            Condition.SNOWING,
        ),
        conditionDescription = "It's very cold out! Wear a heavy jacket."
    ),
    UMBRELLA(
        displayName = "umbrella",
        iconId = R.drawable.ic_umbrella,
        temperatureRange = 33..Int.MAX_VALUE,
        isAccessory = true,
        conditions = listOf(
            Condition.RAINING,
        ),
        conditionDescription = "It's raining! Bring an umbrella."
    ),
    SCARF(
        displayName = "scarf",
        iconId = R.drawable.ic_scarf,
        temperatureRange = Int.MIN_VALUE..32,
        isAccessory = true,
        conditions = listOf(
            Condition.SNOWING,
        ),
        conditionDescription = "It's snowing! You'll need a scarf."
    ),
}

enum class Condition {
    RAINING,
    SNOWING,
    WINDY,
}

