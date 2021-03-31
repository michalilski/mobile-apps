package com.example.todoapp.tasks

import java.io.Serializable
import java.util.*

class Task(val title: String,
           val type: Type,
           val priority: Int,
           val date: Calendar,
           val note: String
) : Serializable