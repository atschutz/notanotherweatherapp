package com.example.notanotherweatherapp.ui

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notanotherweatherapp.getTimeFromDateString
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ClothingChange
import com.example.notanotherweatherapp.model.ForecastRepository
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.model.WeatherCondition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val repository: ForecastRepository,
    private val worker: ForecastScreenWorker,
) : ViewModel() {
    var dailyGroups: List<PeriodGroup> by mutableStateOf(listOf())
    var hourlyGroups: List<PeriodGroup> by mutableStateOf(listOf())

    var cityString by mutableStateOf("")

    // TODO - Don't need.
    var activeClothing: Set<Clothing> by mutableStateOf(setOf())
    var clothingChanges: List<ClothingChange> by mutableStateOf(listOf())

    fun getPeriodsAndClothing(latitude: Double, longitude: Double, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val properties = repository.getLocationProperties(latitude, longitude)

            launch {
                hourlyGroups = worker.mapPeriodsToHourlyGroups(
                    repository.getForecastPeriods(properties?.forecastHourly)
                        ?.take(HOURLY_ITEM_LIMIT) ?: listOf()
                )

                activeClothing = worker.getActiveClothing(hourlyGroups)
                clothingChanges = worker.getHourlyClothingChanges(hourlyGroups)
            }

            launch {
                dailyGroups = (repository.getForecastPeriods(properties?.forecast) ?: listOf())
                    .map {
                        PeriodGroup(
                            period = it,
                            weatherCondition = worker.getWeatherDisplay(it),
                            clothing = null,
                        )
                    }
            }

            launch {
                cityString = Geocoder(context, Locale.getDefault())
                    .getFromLocation(latitude, longitude, 1)
                    ?.firstOrNull()?.locality ?: ""
            }
        }
    }

    fun refresh(latitude: Double, longitude: Double, context: Context): Boolean {
        hourlyGroups = listOf()
        dailyGroups = listOf()
        cityString = ""

        getPeriodsAndClothing(latitude, longitude, context)
        return false
    }

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
                if (period.temperature != null && clothingItem.temperatureRange.contains(period.temperature)) {
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

    fun getHourlyClothingChanges(periodGroups: List<PeriodGroup>): List<ClothingChange> =
        periodGroups.map { group ->
            ClothingChange(
                clothing = Clothing.entries.first {
                    it.temperatureRange.contains(group.period.temperature)
                },
                time = getTimeFromDateString(group.period.startTime ?: ""),
                temperature = group.period.temperature,
            )
        }.distinctBy { it.clothing }

    companion object {
        const val HOURLY_ITEM_LIMIT = 24
    }
}