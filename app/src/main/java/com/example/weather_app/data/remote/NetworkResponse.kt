package com.example.weather_app.data.remote

import com.example.weather_app.data.remote.models.WeatherModel

// T refers to the WeatherModel class
sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: WeatherModel) : NetworkResponse<T>()
    data class Error<out T>(val errorMessage: String) : NetworkResponse<T>()
    data object Loading : NetworkResponse<Nothing>()
}