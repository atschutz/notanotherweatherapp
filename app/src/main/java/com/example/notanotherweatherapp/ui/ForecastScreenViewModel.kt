package com.example.notanotherweatherapp.ui

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ForecastRepository
import com.example.notanotherweatherapp.model.Period
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {
    var hourlyPeriods: List<Period> by mutableStateOf(listOf())
    var dailyPeriods: List<Period> by mutableStateOf(listOf())
    var cityString by mutableStateOf("")

    var activeClothing: Set<Clothing> by mutableStateOf(setOf())

    fun getPeriodsAndClothing(latitude: Double, longitude: Double, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val properties = repository.getLocationProperties(latitude, longitude)

            launch {
                hourlyPeriods = repository.getForecastPeriods(properties?.forecastHourly)
                    ?.take(HOURLY_ITEM_LIMIT) ?: listOf()

                activeClothing = getActiveClothing(hourlyPeriods)
            }

            launch {
                dailyPeriods = repository.getForecastPeriods(properties?.forecast) ?: listOf()
            }

            launch {
                cityString = Geocoder(context, Locale.getDefault())
                    .getFromLocation(latitude, longitude, 1)
                    ?.firstOrNull()?.locality ?: ""
            }
        }

    }

    private fun getActiveClothing(periods: List<Period>): Set<Clothing> {
        val clothing = Clothing.entries.filterNot { it.isAccessory }.toMutableSet()
        val activeClothing = mutableSetOf<Clothing>()

        var hasRain = false
        var hasSnow = false


        periods.forEach { period ->
            // Add active clothing items
            clothing.forEach { item ->
                if (item.temperatureRange.contains(period.temperature)) {
                    activeClothing.add(item)
                }
            }

            clothing.removeAll(activeClothing)

            // Add precipitation items
            if (!hasRain || !hasSnow) {
                period.probabilityOfPrecipitation?.value?.let {
                    if (it > 0) {
                        if ((period.temperature ?: 0) <= FREEZING_POINT) hasSnow = true
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

    fun refresh(latitude: Double, longitude: Double, context: Context): Boolean {
        hourlyPeriods = listOf()
        dailyPeriods = listOf()
        cityString = ""

        getPeriodsAndClothing(latitude, longitude, context)
        return false
    }

    companion object {
        const val FREEZING_POINT = 32
        const val HOURLY_ITEM_LIMIT = 24
        const val NOW_THRESHOLD = 3
    }
}