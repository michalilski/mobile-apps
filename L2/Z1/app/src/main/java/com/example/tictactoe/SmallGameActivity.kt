package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SmallGameActivity : AppCompatActivity() {
    private val gameSize = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_small_game)
        setupBoard()
    }

    private fun setupBoard(){
        for (i in 0 until gameSize*gameSize){
            val stringBtnId = "btn$i"
            val btnId = resources.getIdentifier(stringBtnId, "id", packageName)
            val btn = findViewById<Button>(btnId)
            btn.setTag(R.string.x, i/gameSize) //x position
            btn.setTag(R.string.y, i%gameSize) //y position
            Log.i("SmallGame", "${btn.getTag(R.string.x,)}, ${btn.getTag(R.string.y)}")
        }
    }
}