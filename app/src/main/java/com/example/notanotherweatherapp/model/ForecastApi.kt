package com.example.notanotherweatherapp.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ForecastApi {
    @GET("points/{latitude},{longitude}")
    suspend fun getForecastResponse(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): Response<ForecastResponse>

    @GET
    suspend fun getHourlyResponse(@Url url: String?): Response<HourlyResponse>
}