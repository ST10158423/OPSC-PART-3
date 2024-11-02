package com.example.opscp2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opscp2.databinding.ActivityAreaSelectionBinding
import com.example.opscp2.utils.LocaleHelper
import com.example.opscp2.HikeFromHomeGameActivity

class AreaSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAreaSelectionBinding
    private var tapCounter = 0
    private val handler = Handler(Looper.getMainLooper())
    private var hasLaunchedGame = false  // Prevent multiple game launches

    override fun attachBaseContext(newBase: Context) {
        val sharedPreferences = newBase.getSharedPreferences("Settings", MODE_PRIVATE)
        val language = sharedPreferences.getString("App_Language", "en") ?: "en"
        val context = LocaleHelper.setLocale(newBase, language)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAreaSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up tap listener for background to trigger the game launch
        binding.rootLayout.setOnClickListener {
            if (!hasLaunchedGame) {  // Only count taps if game hasn't launched
                tapCounter++
                if (tapCounter == 5) {
                    launchHiddenGame()  // Launch game if 5 taps detected
                }
                // Reset counter after 2 seconds to avoid unintended activation
                handler.removeCallbacks(resetTapCounter)
                handler.postDelayed(resetTapCounter, 2000)
            }
        }

        // Handle clicks on the area buttons
        binding.btnUmhlanga.setOnClickListener { navigateToRoutes("Umhlanga") }
        binding.btnDurbanNorth.setOnClickListener { navigateToRoutes("Durban North") }
        binding.btnWestville.setOnClickListener { navigateToRoutes("Westville") }
        binding.btnNorthBeach.setOnClickListener { navigateToRoutes("North Beach") }
        binding.btnLaLucia.setOnClickListener { navigateToRoutes("La Lucia") }
        binding.btnBluff.setOnClickListener { navigateToRoutes("Bluff") }
        binding.btnViewMap.setOnClickListener {
            startActivity(Intent(this, RouteDetailsActivity::class.java))
        }
        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun launchHiddenGame() {
        hasLaunchedGame = true  // Set flag to prevent relaunching
        tapCounter = 0  // Reset counter for future use

        // Notify user and launch the hidden game activity
        Toast.makeText(this, "Launching Hike From Home Game!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, HikeFromHomeGameActivity::class.java))
    }

    private val resetTapCounter = Runnable {
        tapCounter = 0
    }

    private fun navigateToRoutes(area: String) {
        val intent = Intent(this, RouteListActivity::class.java)
        intent.putExtra("selected_area", area)
        startActivity(intent)
    }
}
