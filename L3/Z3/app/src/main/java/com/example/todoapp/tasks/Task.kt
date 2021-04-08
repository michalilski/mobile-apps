package com.example.todoapp.tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
class Task(
    @PrimaryKey val uid: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val type: Type,
    @ColumnInfo val priority: Int,
    @ColumnInfo val date: Calendar,
    @ColumnInfo val note: String,
    @ColumnInfo var passed: Boolean = false,
    @ColumnInfo var today: Boolean = false

) : Serializable