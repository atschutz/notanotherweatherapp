package com.example.notanotherweatherapp.model

import com.example.notanotherweatherapp.R

enum class Clothing(
    val displayName: String,
    val iconId: Int,
    val temperatureRange: IntRange,
    val isAccessory: Boolean,
    val conditions: List<Condition>,
) {
    TANK_TOP(
        displayName = "Tank top",
        iconId = R.drawable.ic_tank_top,
        temperatureRange = 85..Int.MAX_VALUE,
        isAccessory = false,
        conditions = listOf(),
    ),
    TEE_SHIRT(
        displayName = "Tee shirt",
        iconId = R.drawable.ic_tee_shirt,
        temperatureRange = 70..84,
        isAccessory = false,
        conditions = listOf(),
    ),
    SWEATSHIRT(
        displayName = "Sweatshirt",
        iconId = R.drawable.ic_sweatshirt,
        temperatureRange = 55..69,
        isAccessory = false,
        conditions = listOf(
            Condition.WINDY,
        ),
    ),
    LIGHT_JACKET(
        displayName = "Light jacket",
        iconId = R.drawable.ic_light_jacket,
        temperatureRange = 40..54,
        isAccessory = false,
        conditions = listOf(
            Condition.WINDY,
            Condition.RAINING,
        ),
    ),
    HEAVY_JACKET(
        displayName = "Heavy jacket",
        iconId = R.drawable.ic_heavy_jacket,
        temperatureRange = Int.MIN_VALUE..39,
        isAccessory = false,
        conditions = listOf(
            Condition.WINDY,
            Condition.SNOWING,
        ),
    ),
    UMBRELLA(
        displayName = "Umbrella",
        iconId = R.drawable.ic_umbrella,
        temperatureRange = 33..Int.MAX_VALUE,
        isAccessory = true,
        conditions = listOf(
            Condition.RAINING,
        ),
    ),
    SCARF(
        displayName = "Scarf",
        iconId = R.drawable.ic_scarf,
        temperatureRange = Int.MIN_VALUE..32,
        isAccessory = true,
        conditions = listOf(
            Condition.SNOWING,
        ),
    ),
}

enum class Condition {
    RAINING,
    SNOWING,
    WINDY,
}

