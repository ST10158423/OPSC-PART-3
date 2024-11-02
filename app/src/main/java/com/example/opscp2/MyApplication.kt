// MyApplication.kt
package com.example.opscp2

import android.app.Application
import android.content.Context
import com.example.opscp2.utils.LocaleHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MyApplication : Application() {

    override fun attachBaseContext(base: Context) {
        val sharedPreferences = base.getSharedPreferences("SettingsPreferences", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language", "en") ?: "en"
        val context = LocaleHelper.setLocale(base, language)
        super.attachBaseContext(context)
    }

    override fun onCreate() {
        super.onCreate()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        FirebaseFirestore.getInstance().firestoreSettings = settings
    }
}
