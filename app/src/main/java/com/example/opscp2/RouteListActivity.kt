package com.example.opscp2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opscp2.databinding.ActivityRouteListBinding
import com.example.opscp2.model.RouteApiModel
import com.example.opscp2.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RouteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRouteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRouteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        // Fetch routes from the API
        fetchRoutesFromApi()
    }

    private fun fetchRoutesFromApi() {
        val call = RetrofitInstance.api.getRoutes()
        call.enqueue(object : Callback<List<RouteApiModel>> {
            override fun onResponse(call: Call<List<RouteApiModel>>, response: Response<List<RouteApiModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { routeList ->
                        // Display the fetched routes in the UI
                        populateRoutes(routeList)
                    }
                } else {
                    Toast.makeText(this@RouteListActivity, "Failed to load routes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<RouteApiModel>>, t: Throwable) {
                Toast.makeText(this@RouteListActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("RouteListActivity", "Error: ${t.message}")
            }
        })
    }

    private fun populateRoutes(routes: List<RouteApiModel>) {
        binding.routeListContainer.removeAllViews()
        for (route in routes) {
            addRouteToLayout(route)
        }
    }

    private fun addRouteToLayout(route: RouteApiModel) {
        // Inflate each route item layout dynamically
        val view = LayoutInflater.from(this).inflate(R.layout.item_route, binding.routeListContainer, false)

        // Get references to TextViews from the inflated layout
        val nameView = view.findViewById<TextView>(R.id.routeName)
        val difficultyView = view.findViewById<TextView>(R.id.routeDifficulty)
        val distanceView = view.findViewById<TextView>(R.id.routeDistance)
        val startPointView = view.findViewById<TextView>(R.id.routeStart)
        val endPointView = view.findViewById<TextView>(R.id.routeEnd)

        // Set the data from the API to the TextViews
        nameView.text = "Name: ${route.name}"
        difficultyView.text = "Difficulty: ${route.difficulty}"
        distanceView.text = "Distance: ${route.distance} km"
        startPointView.text = "Start Point: (${route.start_point.latitude}, ${route.start_point.longitude})"
        endPointView.text = "End Point: (${route.end_point.latitude}, ${route.end_point.longitude})"

        // Add the dynamically created view to the routeListContainer
        binding.routeListContainer.addView(view)
    }
}
