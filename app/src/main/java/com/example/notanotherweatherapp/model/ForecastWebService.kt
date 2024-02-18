package com.example.notanotherweatherapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastWebService {
    private var api: ForecastApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ForecastApi::class.java)
    }
    companion object {
        const val BASE_URL = "https://api.weather.gov/"
    }

    suspend fun getForecastResponse(latitude: Double, longitude: Double) =
        api.getForecastResponse(latitude, longitude)

    suspend fun getHourlyResponse(url: String?) =
        api.getHourlyResponse(url)
}