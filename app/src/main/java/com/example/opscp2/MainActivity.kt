// MainActivity.kt
package com.example.opscp2

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.LocaleListCompat
import com.example.opscp2.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // View Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply saved language settings
        applySavedLocale()
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase settings
        setupFirebase()

        // Button listeners
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /**
     * Configure Firebase Firestore settings, such as offline persistence.
     */
    private fun setupFirebase() {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true) // Enable offline persistence
            .build()
        FirebaseFirestore.getInstance().firestoreSettings = settings
    }

    /**
     * Apply saved language configuration.
     */
    private fun applySavedLocale() {
        // Access shared preferences to get the saved language
        val sharedPreferences: SharedPreferences = getSharedPreferences("SettingsPreferences", MODE_PRIVATE)
        val languageCode = sharedPreferences.getString("language", "en") ?: "en"

        // Set the locale based on the saved language code
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
