package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNewGame()
    }

    private fun createNewGame() {
        game = Game(resources.getStringArray(R.array.words))
        game.setupGame()
        setButtons(game.dict)
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

    override fun onClick(v: View?) {
        val button = v?.let { findViewById<Button>(it.id) }
        if (button != null) {
            Log.i("Clicked ", button.text.toString())
        }
    }
}