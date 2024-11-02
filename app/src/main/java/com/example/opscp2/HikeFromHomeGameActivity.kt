// HikeFromHomeGameActivity.kt
package com.example.opscp2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HikeFromHomeGameActivity : AppCompatActivity() {
    private lateinit var gameView: HikeFromHomeGameView
    private lateinit var tvScore: TextView
    private lateinit var gameOverLayout: View
    private lateinit var btnRestart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hike_from_home_game)

        gameView = findViewById(R.id.gameView)
        tvScore = findViewById(R.id.tvScore)
        gameOverLayout = findViewById(R.id.gameOverLayout)
        btnRestart = findViewById(R.id.btnRestart)

        gameView.setOnGameOverCallback {
            runOnUiThread { showGameOver() }
        }

        btnRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun showGameOver() {
        gameView.stopGame()
        gameOverLayout.visibility = View.VISIBLE
    }

    private fun restartGame() {
        gameOverLayout.visibility = View.GONE
        gameView.startGame()
    }

    override fun onPause() {
        super.onPause()
        gameView.stopGame()
    }

    override fun onResume() {
        super.onResume()
        gameView.startGame()
    }
}
