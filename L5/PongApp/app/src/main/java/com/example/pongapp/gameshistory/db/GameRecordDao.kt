package com.example.pongapp.gameshistory.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pongapp.pong.GameRecord

@Dao
interface GameRecordDao {
    @Query("SELECT * FROM game_record")
    suspend fun getAll(): List<GameRecord>

    @Insert
    suspend fun insert(gameRecord: GameRecord) : Long
}