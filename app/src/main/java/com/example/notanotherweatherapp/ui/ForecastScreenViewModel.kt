package com.example.notanotherweatherapp.ui

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ForecastRepository
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.model.WeatherDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {
    var dailyGroups: List<PeriodGroup> by mutableStateOf(listOf())
    var hourlyGroups: List<PeriodGroup> by mutableStateOf(listOf())

    var cityString by mutableStateOf("")

    var activeClothing: Set<Clothing> by mutableStateOf(setOf())

    fun getPeriodsAndClothing(latitude: Double, longitude: Double, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val properties = repository.getLocationProperties(latitude, longitude)

            launch {
                hourlyGroups = mapPeriodsToHourlyGroups(
                    repository.getForecastPeriods(properties?.forecastHourly)
                        ?.take(HOURLY_ITEM_LIMIT) ?: listOf()
                )

                activeClothing = getActiveClothing(hourlyGroups)
            }

            launch {
                dailyGroups = (repository.getForecastPeriods(properties?.forecast) ?: listOf())
                    .map {
                        PeriodGroup(
                            period = it,
                            weatherDisplay = getWeatherDisplay(it),
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

    private fun getActiveClothing(periodGroups: List<PeriodGroup>): Set<Clothing> {
        val activeClothing = periodGroups
            .filterNot { it.clothing == null }
            .map { it.clothing ?: Clothing.TEE_SHIRT }
            .toMutableSet()

        var hasRain = false
        var hasSnow = false


        periodGroups.forEach { group ->
            // Add precipitation items
            if (!hasRain || !hasSnow) {
                group.period.probabilityOfPrecipitation?.value?.let {
                    if (it > 0) {
                        if ((group.period.temperature ?: 0) <= FREEZING_POINT) hasSnow = true
                        else hasRain = true
                    }
                }
            }
        }

        if (hasRain) activeClothing.add(Clothing.UMBRELLA)
        if (hasSnow) activeClothing.add(Clothing.SCARF)

        // TODO - Account for wind.

        return activeClothing
    }

    private fun mapPeriodsToHourlyGroups(periods: List<Period>) =
        periods.map { period ->
            var clothing: Clothing? = null

            for (clothingItem in Clothing.entries) {
                if (clothingItem.temperatureRange.contains(period.temperature)) {
                    clothing = clothingItem
                    break
                }
            }

            val group = PeriodGroup(
                period = period,
                clothing = clothing,
                weatherDisplay = getWeatherDisplay(period)
            )
            Log.d("-as-", "$group")
            group
        }

    private fun getWeatherDisplay(period: Period): WeatherDisplay? {
        for (weatherDisplayItem in WeatherDisplay.entries) {
            if (weatherDisplayItem.conditions
                    .any {
                        (period.shortForecast ?: "").contains(it, ignoreCase = true) &&
                                period.isDaytime == null ||
                                period.isDaytime == weatherDisplayItem.isForDay
                    }
            ) {
                return weatherDisplayItem
            }
        }
        return null
    }

    fun refresh(latitude: Double, longitude: Double, context: Context): Boolean {
        hourlyGroups = listOf()
        dailyGroups = listOf()
        cityString = ""

        getPeriodsAndClothing(latitude, longitude, context)
        return false
    }

    companion object {
        const val FREEZING_POINT = 32
        const val HOURLY_ITEM_LIMIT = 24
    }
}