package com.example.notanotherweatherapp.model

import android.util.Log
import javax.inject.Inject

class ForecastRepository @Inject constructor(private val webService: ForecastWebService) {
    suspend fun getHourlyPeriods(latitude: Double, longitude: Double): List<Period> {
        val response = webService.getForecastResponse(latitude, longitude)

        return if (response.isSuccessful) {
            getHourlyPeriods(response.body()?.properties?.forecastHourly)
        } else {
            Log.e("Error getting hourly URL", "${response.code()}: ${response.message()}")
            listOf()
        }
    }

    private suspend fun getHourlyPeriods(url: String?): List<Period> =
        webService.getHourlyResponse(url).body()?.properties?.periods ?: listOf()
}