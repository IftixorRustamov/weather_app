package com.example.weather_app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.constants.AppConstant
import com.example.weather_app.data.remote.NetworkResponse
import com.example.weather_app.data.remote.RetrofitInstance
import com.example.weather_app.data.remote.WeatherModel
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


}