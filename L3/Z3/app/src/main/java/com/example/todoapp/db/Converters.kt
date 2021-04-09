package com.example.todoapp.db

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*
import com.example.todoapp.Config
import com.example.todoapp.Utils
import com.example.todoapp.tasks.Type

class Converters {
    private val format = SimpleDateFormat(Config.DATE_FORMAT)

    @TypeConverter
    fun fromCalendar(value: Calendar): String {
        return format.format(value.time)
    }

    @TypeConverter
    fun toCalendar(value: String): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = format.parse(value)
        return calendar
    }

    @TypeConverter
    fun toType(value: Int): Type {
        return Utils.toType[value]!!
    }

    @TypeConverter
    fun toCalendar(value: Type): Int {
        return Utils.fromType[value]!!
    }
}