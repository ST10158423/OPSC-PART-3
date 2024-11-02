// RouteDao.kt
package com.example.opscp2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.opscp2.model.RouteApiModel

@Dao
interface RouteDao {
    @Query("SELECT * FROM routes")
    suspend fun getAllRoutes(): List<RouteApiModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutes(routes: List<RouteApiModel>)

    @Query("DELETE FROM routes")
    suspend fun clearRoutes()
}
