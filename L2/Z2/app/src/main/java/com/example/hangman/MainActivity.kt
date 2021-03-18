package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var game: Game
    private lateinit var guessWordTextView: TextView
    private lateinit var imageView: ImageView
    private var finished = false
    private var allChances = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNewGame()
    }

    private fun createNewGame() {
        game = Game(resources.getStringArray(R.array.words))
        game.setupGame()
        setButtons(game.dict)
        setGuessedWord()
        setImageView()
    }

    private fun setImageView() {
        allChances = game.chancesLeft
        imageView = findViewById(R.id.imageView)
    }

    private fun updateImage() {
        val imageId = resources.getIdentifier("wisielec${allChances - game.chancesLeft}", "drawable", packageName)
        imageView.setImageResource(imageId)
    }

    private fun setButtons(dict: MutableList<String>) {
        for (i in 0 until game.dictSize) {
            val stringBtnId = "button$i"
            val btnId = resources.getIdentifier(stringBtnId, "id", packageName)
            val btn = findViewById<Button>(btnId)
            btn.text = dict[i]
            btn.setOnClickListener(this)
        }
    }

    private fun setGuessedWord() {
        guessWordTextView = findViewById(R.id.guessedWordTextView)
        guessWordTextView.text = game.guessedWord
    }

    private fun updateUI() {
        guessWordTextView.text = game.guessedWord
        game.isFinished()
        if (game.isFinished())
            Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
        if (game.isLost())
            Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show()
        finished = game.isFinished() || game.isLost()
    }

    override fun onClick(v: View?) {
        val button = v?.let { findViewById<Button>(it.id) }
        if (button != null && !finished) {
            val currentChances = game.chancesLeft
            game.updateGuessedWord(button.text[0])
            if (game.chancesLeft == currentChances-1) {
                updateImage()
            }
            updateUI()
        }
    }

    override fun onBackPressed() {
        createNewGame()
        finished = false
        imageView.setImageResource(R.drawable.wisielec0)

    }
}