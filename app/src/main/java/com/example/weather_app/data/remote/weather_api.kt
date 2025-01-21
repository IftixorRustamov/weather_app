package com.example.weather_app.data.remote

import com.example.weather_app.data.remote.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") city: String,
    ): Response<WeatherModel>


    @GET("v1/current.json")
    suspend fun getWeatherDataByLocation(
        @Query("key") apiKey: String,
        @Query("q") latitude: Double,
        @Query("q") longitude: Double
    ): Response<WeatherModel>
}