package com.example.notanotherweatherapp.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ForecastApi {
    @GET("points/{latitude},{longitude}")
    suspend fun getLocationResponse(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
    ): Response<LocationResponse>

    @GET
    suspend fun getForecastResponse(@Url url: String?): Response<ForecastResponse>
}