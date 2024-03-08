package com.example.notanotherweatherapp.ui

import android.util.Log
import com.example.notanotherweatherapp.getTimeFromDateString
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ClothingChange
import com.example.notanotherweatherapp.model.ConditionType
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.model.WeatherCondition
import javax.inject.Inject


class ForecastScreenWorker @Inject constructor() {
    fun mapPeriodsToHourlyGroups(periods: List<Period>) =
        periods.map { period ->
            var clothing: Clothing = Clothing.TEE_SHIRT
            val weatherCondition = getWeatherCondition(period)

            if (weatherCondition == WeatherCondition.RAIN) {
                clothing = Clothing.UMBRELLA
            } else if (weatherCondition == WeatherCondition.SNOW) {
                clothing = Clothing.SCARF
            } else {
                for (clothingItem in Clothing.entries) {
                    if (clothingItem.temperatureRange.contains(period.temperature)) {
                        clothing = clothingItem
                        break
                    }
                }
            }

            // TODO Check precipitation chance because sometimes they say "slight chance".
            val group = PeriodGroup(
                period = period,
                clothing = clothing,
                weatherCondition = getWeatherCondition(period)
            )
            Log.d("-as-", "$group")
            group
        }

    fun getWeatherCondition(period: Period): WeatherCondition {
        for (weatherDisplayItem in WeatherCondition.entries) {
            if (weatherDisplayItem.conditionKeywords
                .any {
                    (period.shortForecast).contains(it, ignoreCase = true) &&
                            (weatherDisplayItem.isForDay == null ||
                                    weatherDisplayItem.isForDay == period.isDaytime)
                }
            ) {
                return weatherDisplayItem
            }
        }
        return WeatherCondition.CLEAR_DAY
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

        return filterClothingChanges(changes)
    }

    fun getAccessoryChanges(periodGroups: List<PeriodGroup>): List<ClothingChange> {
        val changes = periodGroups
            .filter {
                it.weatherCondition.conditionType == ConditionType.PRECIPITATION
            }.map { group ->
                ClothingChange(
                    clothing = group.clothing,
                    time = getTimeFromDateString(group.period.startTime),
                    temperature = group.period.temperature
                )
            }

        return filterClothingChanges(changes)
    }

    private fun filterClothingChanges(changes: List<ClothingChange>): List<ClothingChange> {
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