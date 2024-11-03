package com.example.opscp2

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginButtonOpensMainActivity() {
        // Launch the LoginActivity
        val scenario = ActivityScenario.launch(LoginActivity::class.java)

        // Simulate user entering email and password, then clicking login
        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etEmail).setText("testuser@example.com")
            activity.findViewById<EditText>(R.id.etPassword).setText("password123")
            activity.findViewById<Button>(R.id.btnLogin).performClick()

            // Verify that the Intent for MainActivity has been started
            val expectedIntent = Intent(activity, MainActivity::class.java)
            val actualIntent = activity.intent
            assertEquals(expectedIntent.component?.className, actualIntent.component?.className)
        }
    }
}
