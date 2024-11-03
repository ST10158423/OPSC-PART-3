package com.example.opscp2

import android.content.Context
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsActivityTest {

    @Test
    fun stepGoalIsSavedCorrectly() {
        val scenario = ActivityScenario.launch(SettingsActivity::class.java)
        val sharedPrefs: SharedPreferences = InstrumentationRegistry.getInstrumentation()
            .targetContext.getSharedPreferences("SettingsPreferences", Context.MODE_PRIVATE)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etStepGoal).setText("10000")
            activity.findViewById<Button>(R.id.btnSaveStepGoal).performClick()

            // Check if the step goal was saved in SharedPreferences
            val savedGoal = sharedPrefs.getString("step_goal", "0")
            assertEquals("10000", savedGoal)
        }
    }
}
