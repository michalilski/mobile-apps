package com.example.todoapp.db

import android.content.Context
import androidx.room.Room

class DatabaseConfig {
    lateinit var db : AppDatabase

    fun build(appContext: Context) {
        db = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "tasks-db"
        ).allowMainThreadQueries().build()
    }

}