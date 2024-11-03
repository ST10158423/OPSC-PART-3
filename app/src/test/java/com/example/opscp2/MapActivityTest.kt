package com.example.opscp2

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.gms.maps.SupportMapFragment
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MapActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(RouteDetailsActivity::class.java)

    @Test
    fun mapIsInitialized() {
        val latch = CountDownLatch(1) // Latch to handle asynchronous map initialization

        activityRule.scenario.onActivity { activity ->
            val mapFragment = activity.supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
            mapFragment.getMapAsync { googleMap ->
                assertNotNull("GoogleMap should be initialized", googleMap)
                latch.countDown() // Signal that the map is ready
            }
        }

        // Wait for the map to be initialized or timeout
        val mapInitialized = latch.await(10, TimeUnit.SECONDS)
        assertTrue("Map initialization timed out", mapInitialized)
    }
}
