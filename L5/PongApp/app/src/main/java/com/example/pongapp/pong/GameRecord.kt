package com.example.pongapp.pong

import java.io.Serializable
import java.util.*

class GameRecord(
    val time: Date,
    val scorePlayerOne: Int,
    val scorePlayerTwo: Int
) : Serializable