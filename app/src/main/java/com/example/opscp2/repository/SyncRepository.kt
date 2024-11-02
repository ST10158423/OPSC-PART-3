// SyncRepository.kt
package com.example.opscp2.repository

import com.example.opscp2.model.RouteApiModel
import com.example.opscp2.network.RouteApi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SyncRepository {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun fetchRoutes(): List<RouteApiModel> {
        return try {
            val snapshot = firestore.collection("routes").get().await()  // Fetches routes collection
            snapshot.toObjects(RouteApiModel::class.java)  // Parses documents into Route objects
        } catch (e: Exception) {
            emptyList()  // Returns an empty list if an error occurs
        }
    }
}
