package com.example.notanotherweatherapp.model

import javax.inject.Inject

class ForecastRepository @Inject constructor(webService: ForecastWebService) {
    suspend fun getForecastResponse(latitude: Double?, longitude: Double?) {

    }

    suspend fun getHourlyResponse(url: String?) {
        
    }
}