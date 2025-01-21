package com.example.weather_app.viewModels

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {
    private val _location = MutableLiveData<Location?>()
    val location: LiveData<Location?> get() = _location

    fun updateLocation(location: Location?) {
        _location.value = location
    }
}

//fun AppCompatActivity.hasLocationPermission(): Boolean {
//    return ActivityCompat.checkSelfPermission(
//        this,
//        Manifest.permission.ACCESS_FINE_LOCATION
//    ) == PackageManager.PERMISSION_GRANTED ||
//            ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//}