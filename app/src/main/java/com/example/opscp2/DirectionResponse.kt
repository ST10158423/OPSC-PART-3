package com.example.opscp2

import com.google.android.gms.maps.model.LatLng

data class DirectionsResponse(
    val distance: String,
    val duration: String,
    val steps: String,
    val points: List<LatLng>
)
