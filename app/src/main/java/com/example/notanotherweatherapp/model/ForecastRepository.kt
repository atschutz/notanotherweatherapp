package com.example.notanotherweatherapp.model

import android.util.Log
import javax.inject.Inject

class ForecastRepository @Inject constructor(private val webService: ForecastWebService) {
    suspend fun getLocationProperties(latitude: Double, longitude: Double): LocationProperties? {
        val response = webService.getLocationResponse(latitude, longitude)

        return if (response.isSuccessful) {
            response.body()?.properties
        } else {
            Log.e(
                "Error getting location properties",
                "${response.code()}: ${response.message()}"
            )
            null
        }
    }

    suspend fun getForecastProperties(url: String?) =
        webService.getForecastResponse(url).body()?.properties?.periods
}