package com.example.opscp2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opscp2.utils.LocaleHelper

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spinnerLanguage: Spinner
    private lateinit var switchOfflineMode: Switch
    private lateinit var switchTheme: Switch
    private lateinit var etStepGoal: EditText
    private lateinit var btnSaveStepGoal: Button
    private lateinit var tvStepsTaken: TextView
    private lateinit var tvDistanceCovered: TextView
    private lateinit var tvCaloriesBurned: TextView

    private var isLanguageChanged = false

    override fun attachBaseContext(newBase: Context) {
        val sharedPreferences = newBase.getSharedPreferences("SettingsPreferences", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language", "en") ?: "en"
        val context = LocaleHelper.setLocale(newBase, language)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferences = getSharedPreferences("SettingsPreferences", MODE_PRIVATE)

        // Initialize views
        spinnerLanguage = findViewById(R.id.spinnerLanguage)
        switchOfflineMode = findViewById(R.id.switchOfflineMode)
        switchTheme = findViewById(R.id.switchTheme)
        etStepGoal = findViewById(R.id.etStepGoal)
        btnSaveStepGoal = findViewById(R.id.btnSaveStepGoal)
        tvStepsTaken = findViewById(R.id.tvStepsTaken)
        tvDistanceCovered = findViewById(R.id.tvDistanceCovered)
        tvCaloriesBurned = findViewById(R.id.tvCaloriesBurned)

        setupLanguageSpinner()
        setupToggleSwitches()
        setupSaveStepGoalButton()
    }

    private fun setupLanguageSpinner() {
        val languages = resources.getStringArray(R.array.language_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLanguage.adapter = adapter

        val savedLanguage = sharedPreferences.getString("language", "en")
        spinnerLanguage.setSelection(
            when (savedLanguage) {
                "en" -> 0
                "zu" -> 1
                "af" -> 2
                else -> 0
            }
        )

        spinnerLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val languageCode = when (position) {
                    0 -> "en"
                    1 -> "zu"
                    2 -> "af"
                    else -> "en"
                }
                if (savedLanguage != languageCode && !isLanguageChanged) {
                    isLanguageChanged = true
                    applyLanguageChange(languageCode)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun applyLanguageChange(languageCode: String) {
        sharedPreferences.edit().putString("language", languageCode).apply()
        LocaleHelper.setLocale(this, languageCode)
        recreate()
        isLanguageChanged = false
    }

    private fun setupToggleSwitches() {
        val isOfflineMode = sharedPreferences.getBoolean("offlineMode", false)
        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)

        switchOfflineMode.isChecked = isOfflineMode
        switchTheme.isChecked = isDarkMode

        switchOfflineMode.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("offlineMode", isChecked).apply()
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("darkMode", isChecked).apply()
            // Implement dark mode theme change logic here
        }
    }

    private fun setupSaveStepGoalButton() {
        btnSaveStepGoal.setOnClickListener {
            val stepGoal = etStepGoal.text.toString().toIntOrNull()
            if (stepGoal == null || stepGoal <= 0) {
                Toast.makeText(this, "Please enter a valid step goal", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Calculate hike summary
            val distanceCovered = calculateDistance(stepGoal)
            val caloriesBurned = calculateCalories(stepGoal)

            // Update the summary UI
            tvStepsTaken.text = "Steps Taken: $stepGoal"
            tvDistanceCovered.text = "Distance Covered: %.2f km".format(distanceCovered)
            tvCaloriesBurned.text = "Calories Burned: %.0f kcal".format(caloriesBurned)

            // Optionally, save the step goal in SharedPreferences
            sharedPreferences.edit().putInt("stepGoal", stepGoal).apply()
        }
    }

    private fun calculateDistance(steps: Int): Double {
        val stepLengthInKm = 0.0008  // Approximate distance per step in km
        return steps * stepLengthInKm
    }

    private fun calculateCalories(steps: Int): Double {
        val caloriesPerStep = 0.04  // Approximate calories burned per step
        return steps * caloriesPerStep
    }
}
