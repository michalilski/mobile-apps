package com.example.pongapp.gameshistory.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pongapp.pong.GameRecord

@Database(entities = [GameRecord::class], version = 6)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameRecordDao(): GameRecordDao
}