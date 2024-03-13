package com.example.notanotherweatherapp.ui

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notanotherweatherapp.Preference
import com.example.notanotherweatherapp.PreferenceManager
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ClothingChange
import com.example.notanotherweatherapp.model.ForecastRepository
import com.example.notanotherweatherapp.model.PeriodGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val repository: ForecastRepository,
    private val worker: ForecastScreenWorker,
    private val preferenceManager: PreferenceManager,
) : ViewModel() {
    var dailyGroups: List<PeriodGroup> by mutableStateOf(listOf())
    var hourlyGroups: List<PeriodGroup> by mutableStateOf(listOf())
    val plannedHourlyGroups = derivedStateOf {
        hourlyGroups.take(preferenceManager.getPreference(Preference.DISPLAY_HOURS).toInt())
    }
    val laterHourlyGroups = derivedStateOf {
        hourlyGroups.takeLast(
            hourlyGroups.size - preferenceManager.getPreference(Preference.DISPLAY_HOURS)
                .toInt()
        )
    }

    var cityString by mutableStateOf("")

    var clothingChanges: List<ClothingChange> by mutableStateOf(listOf())
    var accessoryChanges: List<ClothingChange> by mutableStateOf(listOf())
    val activeClothing = derivedStateOf {
        (clothingChanges + accessoryChanges)
            .distinctBy { it.clothing }
            .map { it.clothing }
    }

    fun getPeriodsAndClothing(latitude: Double, longitude: Double, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val properties = repository.getLocationProperties(latitude, longitude)

            launch {
                hourlyGroups = worker.mapPeriodsToHourlyGroups(
                    repository.getForecastPeriods(properties?.forecastHourly)
                        ?.take(HOURLY_ITEM_LIMIT) ?: listOf()
                )

                clothingChanges = worker.getHourlyClothingChanges(hourlyGroups)
                accessoryChanges = worker.getAccessoryChanges(hourlyGroups)
                Log.d("-as-", "$hourlyGroups")
            }

            launch {
                dailyGroups = (repository.getForecastPeriods(properties?.forecast) ?: listOf())
                    .map {period ->
                        PeriodGroup(
                            period = period,
                            weatherCondition = worker.getWeatherCondition(period),
                            clothing = Clothing.entries.first {
                                it.temperatureRange.contains(period.temperature)
                            },
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

    companion object {
        const val HOURLY_ITEM_LIMIT = 24
    }
}