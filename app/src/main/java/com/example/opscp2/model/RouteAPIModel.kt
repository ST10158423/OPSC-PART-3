package com.example.opscp2.model


// Data class representing a route fetched from the API
data class RouteApiModel(
    val id: String = "",             // Unique identifier of the route (could be Firestore document ID or custom ID)
    val name: String = "",           // Route name (e.g., "Trail A")
    val distance: Double = 0.0,      // Distance in kilometers
    val difficulty: String = "",     // Difficulty level (e.g., "Moderate")
    val start_point: LocationPoint = LocationPoint(),  // Start point coordinates as a nested object
    val end_point: LocationPoint = LocationPoint()     // End point coordinates as a nested object
)

// Nested data class for start and end points
data class LocationPoint(
    val latitude: Double = 0.0,      // Latitude coordinate
    val longitude: Double = 0.0      // Longitude coordinate
)
