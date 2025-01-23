package com.example.weather_app.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.R
import com.example.weather_app.constants.AppConstant
import com.example.weather_app.data.remote.NetworkResponse
import com.example.weather_app.data.remote.RetrofitInstance
import com.example.weather_app.data.remote.models.WeatherModel
import kotlinx.coroutines.launch
import okio.IOException

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult


    fun getData(city: String) {
        viewModelScope.launch {
            _weatherResult.value = NetworkResponse.Loading
            try {
                val response = weatherApi.getWeatherData(AppConstant.apiKey, city)
                if (response.isSuccessful && response.body() != null) {
                    _weatherResult.value = NetworkResponse.Success(response.body()!!)
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            } catch (e: IOException) {
                _weatherResult.value = NetworkResponse.Error("Your Network is not connected")
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Unexpected Error is occurred")
            }
        }
    }


    fun getDataByLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _weatherResult.value = NetworkResponse.Loading
            try {
                val response = weatherApi.getWeatherDataByLocation(
                    AppConstant.apiKey,
                    latitude,
                    longitude
                )
                if (response.isSuccessful && response.body() != null) {
                    _weatherResult.value = NetworkResponse.Success(response.body()!!)
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            } catch (e: IOException) {
                _weatherResult.value = NetworkResponse.Error("Your Network is not connected")
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Unexpected Error occurred")
            }
        }
    }
}

val listIcons: Map<String, Int> = mapOf(
    "clear" to R.drawable.sun,
    "sunny" to R.drawable.sun,
    "rain" to R.drawable.rainy,
    "cloudy" to R.drawable.cloudy,
    "snow" to R.drawable.snow
)

fun getWeatherIcon(condition: String): Int {
    return listIcons.entries.firstOrNull { (key, _) ->
        condition.lowercase().contains(key)
    }?.value ?: R.drawable.no_data
}