package com.example.pongapp.gameshistory.db

import android.content.Context
import androidx.room.Room
import com.example.pongapp.pong.GameRecord

class DbController {
    companion object {
        private lateinit var db: GameDatabase
        fun setupDb(context: Context) {
            db = Room.databaseBuilder(
                context,
                GameDatabase::class.java, "game-database"
            ).fallbackToDestructiveMigration().build()
        }

        suspend fun loadData(): ArrayList<GameRecord> {
            return db.gameRecordDao().getAll() as ArrayList<GameRecord>
        }

        suspend fun insert(gameRecord: GameRecord) : Long{
            return db.gameRecordDao().insert(gameRecord)
        }
    }
}