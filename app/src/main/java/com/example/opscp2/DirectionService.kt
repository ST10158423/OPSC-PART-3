package com.example.opscp2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsService {
    // Google Directions API to fetch the route
    @GET("maps/api/directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): Response<DirectionsResponse>
}
