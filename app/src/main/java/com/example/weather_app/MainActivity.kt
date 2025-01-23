package com.example.weather_app

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weather_app.presentation.pages.Homepage
import com.example.weather_app.ui.theme.Weather_appTheme
import com.example.weather_app.presentation.viewModels.LocationViewModel
import com.example.weather_app.presentation.viewModels.WeatherViewModel

class MainActivity : ComponentActivity() {
    private lateinit var locationManagerAPI: LocationManagerAPI
    private val locationViewModel: LocationViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // replaces XML layout to Jetpack Compose
        setContent {
            Weather_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Homepage(weatherViewModel, locationViewModel)
                }

            }
        }

        locationManagerAPI = LocationManagerAPI(this)

        // Fetch the current location if permissions are granted
        if (hasLocationPermission()) {
            val location = locationManagerAPI.getCurrentLocation()
            locationViewModel.updateLocation(location)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                1001
            )
        }
    }


    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this, ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

}
