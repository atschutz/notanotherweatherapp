package com.example.notanotherweatherapp.ui

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notanotherweatherapp.model.ForecastRepository
import com.example.notanotherweatherapp.model.Period
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    fun getPeriods(latitude: Double, longitude: Double, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val properties = repository.getLocationProperties(latitude, longitude)

            val hourlyPeriodsDef = async {
                repository.getForecastProperties(properties?.forecastHourly)
                    ?.take(HOURLY_ITEM_LIMIT) ?: listOf()
            }

            val dailyPeriodsDef = async {
                repository.getForecastProperties(properties?.forecast) ?: listOf()
            }

            val cityStringDef = async {
                Geocoder(context, Locale.getDefault())
                    .getFromLocation(latitude, longitude, 1)
                    ?.firstOrNull()?.locality ?: ""
            }

            hourlyPeriods = hourlyPeriodsDef.await()
            dailyPeriods = dailyPeriodsDef.await()
            cityString = cityStringDef.await()
        }

    }

    companion object {
        const val HOURLY_ITEM_LIMIT = 24
    }
}