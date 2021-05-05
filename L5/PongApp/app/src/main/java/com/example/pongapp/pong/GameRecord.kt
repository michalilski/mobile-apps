package com.example.pongapp.pong

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "game_record")
class GameRecord(
    @ColumnInfo(name = "date") val time: Date,
    @ColumnInfo(name = "score_player_one") val scorePlayerOne: Int,
    @ColumnInfo(name = "score_player_two") val scorePlayerTwo: Int
) : Serializable {
    @PrimaryKey(autoGenerate = true) var uid : Long = 0
}