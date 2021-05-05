package com.example.pongapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pongapp.gameshistory.CustomAdapter
import com.example.pongapp.gameshistory.db.DbController
import com.example.pongapp.pong.GameRecord
import com.example.pongapp.pong.PongActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


//TODO
//zapisywanie gry
class MainActivity : AppCompatActivity() {
    private lateinit var customAdapter : CustomAdapter
    private var games = ArrayList<GameRecord>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        DbController.setupDb(this)
        setup()
    }

    override fun onResume() {
        super.onResume()
        val lastGame = intent.getSerializableExtra("lastGame") as GameRecord?
        if(lastGame != null) {
            val winner = if (lastGame.scorePlayerOne == 3) {
                "One"
            } else {
                "Two"
            }
            Toast.makeText(
                this,
                "Game finished with score ${lastGame.scorePlayerOne} - ${lastGame.scorePlayerTwo}. " +
                        "Player${winner} Won!", Toast.LENGTH_LONG
            ).show()
            val id = insertGame(lastGame)
            lastGame.uid = id
        }
        games = loadGames()
        customAdapter.dataSet = games
        customAdapter.notifyDataSetChanged()
    }

    private fun setup() {
        val newGameButton = findViewById<Button>(R.id.newGameButton)
        newGameButton.setOnClickListener {
            val intent = Intent(this, PongActivity::class.java)
            startActivity(intent)
        }

        customAdapter = CustomAdapter(games)
        val recyclerView = findViewById<RecyclerView>(R.id.gameHistoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
    }

    private fun insertGame(lastGame : GameRecord) : Long{
        var id = -2L
        runBlocking{
            id = DbController.insert(lastGame)
        }
        return id
    }

    private fun loadGames() : ArrayList<GameRecord>{
        games = ArrayList()
        runBlocking{
            games = DbController.loadData()
        }
        return games
    }
}