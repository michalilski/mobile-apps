package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun chooseSmallGame(v: View) {
        val intent = Intent(this, SmallGameActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun chooseBigGame(v: View) {

    }
}