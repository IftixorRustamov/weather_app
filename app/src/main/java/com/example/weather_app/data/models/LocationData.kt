package com.example.weather_app.data.models

data class LocationData(
    val name: String,
    val country: String,
    val region: String,
    val timezoneId: String,
    val coordinates: Coordinates,
    val timeInfo: TimeInfo
)