package com.example.opscp2


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opscp2.databinding.ActivityRouteDetailsBinding
import com.example.opscp2.DirectionsResponse
import com.example.opscp2.RetrofitInstance
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.*

class RouteDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityRouteDetailsBinding
    private lateinit var map: GoogleMap

    // Google Maps API key
    private val apiKey = "AIzaSyDNc00yQFbwA9q3o2PSDRsi4Ck_J18XUQc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityRouteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Google Map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Confirm Button functionality
        binding.btnConfirm.setOnClickListener {
            Toast.makeText(this, "Route confirmed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Set start and end points (You can replace these with dynamic locations)
        val startPoint = LatLng(-29.79656305614323, 31.03569289878209)  //VCDN
        val endPoint = LatLng(-29.795287518702306, 31.039426533652723)  // Japanese Gardens

        map.addMarker(MarkerOptions().position(startPoint).title("Start Point"))
        map.addMarker(MarkerOptions().position(endPoint).title("End Point"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 12f))

        // Fetch the route from Google Directions API using Retrofit
        fetchRoute(startPoint, endPoint)
    }

    private fun fetchRoute(start: LatLng, end: LatLng) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Use Retrofit instance to make API call
                val response = RetrofitInstance.api.getDirections(
                    "${start.latitude},${start.longitude}",
                    "${end.latitude},${end.longitude}",
                    apiKey
                )

                // Handle the response
                if (response.isSuccessful) {
                    val route = response.body()
                    withContext(Dispatchers.Main) {
                        if (route != null) {
                            drawRoute(route)
                            updateRouteInfo(route)
                        }
                    }
                } else {
                    Log.e("RouteDetailsActivity", "Error fetching route: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("RouteDetailsActivity", "Exception occurred: ${e.message}")
            }
        }
    }

    private fun drawRoute(route: DirectionsResponse) {
        val polylineOptions = PolylineOptions().addAll(route.points).width(10f).color(R.color.black)
        map.addPolyline(polylineOptions)
    }

    private fun updateRouteInfo(route: DirectionsResponse) {
        // Update UI with route info (distance, time, steps)
        binding.tvDistance.text = route.distance
        binding.tvDuration.text = route.duration
        binding.tvSteps.text = route.steps
    }


}
