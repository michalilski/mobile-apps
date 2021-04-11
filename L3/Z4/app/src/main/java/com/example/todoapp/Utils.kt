package com.example.todoapp

import com.example.todoapp.tasks.Type

class Utils {
    companion object{
        val priorityMap: Map<Int, Int> = mapOf(
                0 to R.drawable.small_low,
                1 to R.drawable.small_medium,
                2 to R.drawable.small_high,
        )
        val typeMap: Map<Type, Int> = mapOf(
                Type.HOME to R.drawable.home,
                Type.GYM to R.drawable.gym,
                Type.WORK to R.drawable.work
        )
//      database
        val toType: Map<Int, Type> = mapOf(
                0 to Type.HOME,
                1 to Type.GYM,
                2 to Type.WORK,
        )

        val fromType: Map<Type, Int> = mapOf(
                Type.HOME to 0,
                Type.GYM to 1,
                Type.WORK to 2,
        )
    }
}