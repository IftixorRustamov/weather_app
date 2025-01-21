//package com.example.weather_app
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.location.LocationManager
//import android.os.Looper
//import com.example.weather_app.data.remote.Location
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationResult
//import com.google.android.gms.location.Priority
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.launch
//
//class DefaultLocationClient(
//    private val context: Context,
//    private val client: FusedLocationProviderClient // used to get user's current location
//) : LocationClient {
//
//    @SuppressLint("MissingPermission")
//    override fun getLocationUpdates(interval: Long): Flow<Location> = callbackFlow {
//        if (!context.hasLocationPermission()) {
//            throw LocationClient.LocationException("Missing location permission")
//        }
//
//        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//
//        if (!isGpsEnabled && !isNetworkEnabled) {
//            throw LocationClient.LocationException("GPS is disabled")
//        }
//
//        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
//            .setMinUpdateIntervalMillis(
//                interval
//            ).build()
//
//        val locationCallBack = object : LocationCallback() {
//            override fun onLocationResult(result: LocationResult) {
//                super.onLocationResult(result)
//                result.locations.lastOrNull()?.let { location ->
//                    launch {
//                        send(location)
//                    }
//                }
//            }
//
//        }
//
//        client.requestLocationUpdates(
//            request,
//            locationCallBack,
//            Looper.getMainLooper()
//        )
//
//        awaitClose {
//            client.removeLocationUpdates(locationCallback)
//        }
//
//    }
//}
//
