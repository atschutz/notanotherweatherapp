package com.example.notanotherweatherapp.ui

import android.util.Log
import com.example.notanotherweatherapp.getTimeFromDateString
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ClothingChange
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.model.WeatherCondition
import javax.inject.Inject


class ForecastScreenWorker @Inject constructor() {
    fun getActiveClothing(periodGroups: List<PeriodGroup>): Set<Clothing> {
        val activeClothing = periodGroups
            .filterNot { it.clothing == null }
            .map { it.clothing ?: Clothing.TEE_SHIRT }
            .toMutableSet()

        if (periodGroups.any { it.weatherCondition == WeatherCondition.RAIN })
            activeClothing.add(Clothing.UMBRELLA)

        if (periodGroups.any { it.weatherCondition == WeatherCondition.SNOW })
            activeClothing.add(Clothing.SCARF)

        // TODO - Account for wind.

        return activeClothing
    }

    fun mapPeriodsToHourlyGroups(periods: List<Period>) =
        periods.map { period ->
            var clothing: Clothing? = null

            for (clothingItem in Clothing.entries) {
                if (clothingItem.temperatureRange.contains(period.temperature)) {
                    clothing = clothingItem
                    break
                }
            }

            // TODO Check precipitation chance because sometimes they say "slight chance".
            val group = PeriodGroup(
                period = period,
                clothing = clothing,
                weatherCondition = getWeatherDisplay(period)
            )
            Log.d("-as-", "$group")
            group
        }

    fun getWeatherDisplay(period: Period): WeatherCondition? {
        for (weatherDisplayItem in WeatherCondition.entries) {
            if (weatherDisplayItem.conditionKeywords
                .any {
                    (period.shortForecast ?: "").contains(it, ignoreCase = true) &&
                            (weatherDisplayItem.isForDay == null ||
                                    weatherDisplayItem.isForDay == period.isDaytime)
                }
            ) {
                return weatherDisplayItem
            }
        }
        return null
    }

    fun getHourlyClothingChanges(periodGroups: List<PeriodGroup>): List<ClothingChange> {
        val changes = periodGroups.map { group ->
            ClothingChange(
                clothing = Clothing.entries.first {
                    it.temperatureRange.contains(group.period.temperature)
                },
                time = getTimeFromDateString(group.period.startTime ?: ""),
                temperature = group.period.temperature,
            )
        }

        val filtered = mutableListOf<ClothingChange>()

        changes.firstOrNull()?.let { filtered.add(it) }

        for (i in 1..changes.lastIndex) {
            if (changes[i].clothing != changes[i - 1].clothing) {
                filtered.add(changes[i])
            }
        }

        return filtered
    }
}