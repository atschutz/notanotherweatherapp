package com.example.notanotherweatherapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notanotherweatherapp.model.ForecastRepository
import com.example.notanotherweatherapp.model.Period
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastScreenViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {
    var periods: List<Period> by mutableStateOf(listOf())

    fun getPeriods(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            periods = repository.getHourlyPeriods(latitude, longitude).take(13)
        }
    }
}