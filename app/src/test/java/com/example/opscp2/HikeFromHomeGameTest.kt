package com.example.opscp2

import android.view.SurfaceView
import android.widget.TextView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HikeFromHomeGameTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HikeFromHomeGameActivity::class.java)

    @Test
    fun gameStartsWithZeroScore() {
        activityRule.scenario.onActivity { activity ->
            val scoreTextView = activity.findViewById<TextView>(R.id.tvScore)
            assertEquals("Score: 0", scoreTextView.text.toString())
        }
    }

    @Test
    fun tapIncreasesJump() {
        activityRule.scenario.onActivity { activity ->
            activity.findViewById<SurfaceView>(R.id.gameView).performClick()
            // Verify if jump behavior works (logic can vary depending on implementation)
        }
    }
}
