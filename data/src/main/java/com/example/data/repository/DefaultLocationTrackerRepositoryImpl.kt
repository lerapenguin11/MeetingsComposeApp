package com.example.data.repository

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.domain.repository.location.LocationTrackerRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale

class DefaultLocationTrackerRepositoryImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val context: Context
) : LocationTrackerRepository {

    override suspend fun getCurrentLocation(): String? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = context.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager

        val isGpsEnabled = locationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!isGpsEnabled && !(hasAccessCoarseLocationPermission || hasAccessFineLocationPermission)) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            fusedLocationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(
                            getCityName(
                                lat = result.latitude,
                                long = result.longitude
                            )
                        ) { cause, _, _ -> }
                    } else {
                        cont.resume(null) { cause, _, _ -> }
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(
                        getCityName(
                            lat = it?.latitude,
                            long = it?.longitude
                        )
                    ) { cause, _, _ -> }
                }
                addOnFailureListener {
                    cont.resume(null) { cause, _, _ -> }
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    private fun getCityName(lat: Double?, long: Double?): String? {
        val geocoder = Geocoder(context, Locale(LANGUAGE))
        val addresses = lat?.let { latitude ->
            long?.let { longitude ->
                geocoder.getFromLocation(latitude, longitude, MAX_RESULT)
            }
        }
        return addresses?.get(0)?.locality
    }
}

private const val LANGUAGE = "ru"
private const val MAX_RESULT = 1