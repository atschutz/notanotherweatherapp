package com.example.notanotherweatherapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ForecastWebService @Inject constructor() {
    private var api: ForecastApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ForecastApi::class.java)
    }

    suspend fun getLocationResponse(latitude: Double, longitude: Double) =
        api.getLocationResponse(latitude, longitude)

    suspend fun getForecastResponse(url: String?) =
        api.getForecastResponse(url)

    companion object {
        const val BASE_URL = "https://api.weather.gov/"
    }
}