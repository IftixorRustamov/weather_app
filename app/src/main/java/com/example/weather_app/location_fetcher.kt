import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat

class LocationManagerAPI(private val context: Context) {
    private var currentLocation: Location? = null
    private lateinit var locationManager: LocationManager

    private var gpsLocationListener: LocationListener? = null
    private var networkLocationListener: LocationListener? = null

    private val handler = Handler()

    fun getCurrentLocation(): Location? {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Check permissions at runtime
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return null
        }

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!hasGps && !hasNetwork) {
            return null
        }

        gpsLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                currentLocation = location
                locationManager.removeUpdates(this)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        networkLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                currentLocation = location
                locationManager.removeUpdates(this)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        if (hasGps) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                gpsLocationListener!!
            )
        }

        if (hasNetwork) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F,
                networkLocationListener!!
            )
        }

        // Fetch last known location from both providers
        val gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        // Choose the more accurate location
        currentLocation = when {
            gpsLocation != null && networkLocation != null -> {
                if (gpsLocation.accuracy <= networkLocation.accuracy) gpsLocation else networkLocation
            }

            gpsLocation != null -> gpsLocation
            networkLocation != null -> networkLocation
            else -> null
        }

        // Stop receiving updates after 10 seconds if no location has been found
        handler.postDelayed({
            locationManager.removeUpdates(gpsLocationListener!!)
            locationManager.removeUpdates(networkLocationListener!!)
        }, 10000)

        return currentLocation
    }
}
