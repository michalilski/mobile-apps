package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BigGameActivity : AppCompatActivity(), View.OnClickListener {

    private val gameSize = 5
    private val game = GameLogic(gameSize)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_game)
        setupButtons()
        game.setupGame()
    }

    private fun setupButtons() {
        var stringBtnId = "menuButton"
        var btnId = resources.getIdentifier(stringBtnId, "id", packageName)
        var btn = findViewById<Button>(btnId)
        btn.setOnClickListener(this)
        for (i in 0 until gameSize * gameSize) {
            stringBtnId = "btn$i"
            btnId = resources.getIdentifier(stringBtnId, "id", packageName)
            btn = findViewById<Button>(btnId)
            btn.setTag(R.string.x, i / gameSize)
            btn.setTag(R.string.y, i % gameSize)
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        val currentButton = v?.let { findViewById<Button>(it.id) }
        if (currentButton?.id == R.id.menuButton) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            if (currentButton != null && !game.gameFinished) {
                val x = currentButton.getTag(R.string.x) as Int
                val y = currentButton.getTag(R.string.y) as Int
                if (game.gameBoard[x][y] == 0) {
                    game.gameBoard[x][y] = game.currentPlayer
                    currentButton.text = game.currentPlayerSymbol[game.currentPlayer]
                    game.gameFinished = game.checkResult()
                    if (game.gameFinished) {
                        showGameResult()
                    }
                    game.currentPlayer *= -1
                }
            }
        }
    }

    private fun showGameResult() {
        Toast.makeText(applicationContext, "Game finished. Player ${game.currentPlayerSymbol[game.currentPlayer]} won.", Toast.LENGTH_LONG).show()
    }

}