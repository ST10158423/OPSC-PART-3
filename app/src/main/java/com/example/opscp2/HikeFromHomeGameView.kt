// HikeFromHomeGameView.kt
package com.example.opscp2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class HikeFromHomeGameView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var hikerY = 0f
    private var hikerJumping = false
    private var score = 0
    private var obstacleX = width.toFloat()
    private var obstacleY = height - 200f
    private val paint = Paint()
    private val gameHandler = Handler(Looper.getMainLooper())
    private lateinit var onGameOver: () -> Unit // Callback for game over

    private val gameLoop = object : Runnable {
        override fun run() {
            updateGame()
            invalidate()
            gameHandler.postDelayed(this, 30) // Game loop interval
        }
    }

    init {
        paint.textSize = 50f
        startGame()
    }

    fun startGame() {
        hikerY = height - 200f
        obstacleX = width.toFloat()
        score = 0
        gameHandler.post(gameLoop)
    }

    fun setOnGameOverCallback(callback: () -> Unit) {
        onGameOver = callback
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.GREEN
        canvas.drawRect(100f, hikerY, 200f, hikerY + 100f, paint)

        paint.color = Color.GRAY
        canvas.drawRect(obstacleX, obstacleY, obstacleX + 100f, obstacleY + 100f, paint)

        paint.color = Color.WHITE
        canvas.drawText("Score: $score", 50f, 100f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && !hikerJumping) {
            hikerJumping = true
        }
        return true
    }

    private fun updateGame() {
        obstacleX -= 10
        if (obstacleX < 0) {
            obstacleX = width.toFloat()
            obstacleY = height - 200f + Random.nextInt(-100, 100)
            score += 10
        }

        if (hikerJumping) {
            hikerY -= 20
            if (hikerY < height - 400) hikerJumping = false
        } else if (hikerY < height - 200f) {
            hikerY += 20
        }

        if (obstacleX < 200f && obstacleX > 100f && hikerY + 100f >= obstacleY) {
            onGameOver()
        }
    }

    fun stopGame() {
        gameHandler.removeCallbacks(gameLoop)
    }
}
