package com.example.opscp2.network

import com.example.opscp2.model.RouteApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface RouteApi {

    // Fetch all routes from the API
    @GET("routes")
    fun getRoutes(): Call<List<RouteApiModel>>

    // Add a new route to the API
    @POST("routes")
    fun postRoute(@Body newRoute: RouteApiModel): Call<RouteApiModel>
}

