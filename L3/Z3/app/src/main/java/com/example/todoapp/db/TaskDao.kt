package com.example.todoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverter
import com.example.todoapp.tasks.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert
    fun insertAll(tasks: ArrayList<Task>)

    @Query("DELETE FROM task")
    fun nuke()
}