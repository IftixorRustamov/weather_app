package com.example.weather_app.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") city: String,
    ): Response<WeatherModel>
}