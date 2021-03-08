package com.example.z2_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    private var playerScore = 0
    private var enemyScore = 0
    private lateinit var moveMap: HashMap<Int, String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startGame()
    }

    private fun startGame() {
        playerScore = 0
        enemyScore = 0
        moveMap = HashMap()
        moveMap[0] = "Rock"
        moveMap[1] = "Paper"
        moveMap[2] = "Scissors"
    }

    private fun enemyMove(): Int {
        return ThreadLocalRandom.current().nextInt(0, 3)
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        val enemyScoreTextView = findViewById<View>(R.id.enemyScoreTextView) as TextView
        enemyScoreTextView.text = "Enemy Score: $enemyScore"
        val playerScoreTextView = findViewById<View>(R.id.playerScoreTextView) as TextView
        playerScoreTextView.text = "Player Score: $playerScore"
    }

    fun playRock(view: View?) {
        val enemyMove = enemyMove()
        val moveInfo = "Enemy chose ${moveMap[enemyMove]} ."
        var resultInfo = ""
        when (enemyMove) {
            0 -> {
                resultInfo = "Draw."
            }
            1 -> {
                resultInfo = "You lost."
                enemyScore++
            }
            2 -> {
                resultInfo = "You won."
                playerScore++
            }
        }
        showResult(moveInfo + resultInfo)
    }

    fun playPaper(view: View?) {
        val enemyMove = enemyMove()
        val moveInfo = "Enemy chose ${moveMap[enemyMove]} ."
        var resultInfo = ""
        when (enemyMove) {
            0 -> {
                resultInfo = "You won."
                playerScore++
            }
            1 -> {
                resultInfo = "Draw."
            }
            2 -> {
                resultInfo = "You lost."
                enemyScore++
            }
        }
        showResult(moveInfo + resultInfo)
    }

    fun playScissors(view: View?) {
        val enemyMove = enemyMove()
        val moveInfo = "Enemy chose ${moveMap[enemyMove]} ."
        var resultInfo = ""
        when (enemyMove) {
            0 -> {
                resultInfo = "You lost."
                enemyScore++
            }
            1 -> {
                resultInfo = "You won."
                playerScore++
            }
            2 -> {
                resultInfo = "Draw."
            }
        }
        showResult(moveInfo + resultInfo)
    }
}