package com.example.todoapp

import com.example.todoapp.tasks.Type

class Utils {
    companion object{
        val priorityMap: Map<Int, String> = mapOf(
                0 to Config.LOW_PRIORITY,
                1 to Config.MEDIUM_PRIORITY,
                2 to Config.HIGH_PRIORITY,
        )
        val typeMap: Map<Type, String> = mapOf(
                Type.HOME to "Home",
                Type.GYM to "Gym",
                Type.WORK to "Work"
        )
    }
}