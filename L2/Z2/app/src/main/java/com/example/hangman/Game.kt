package com.example.hangman

import android.util.Log

class Game(private val words: Array<String>){
    private lateinit var word: String
    lateinit var dict: MutableList<String>
    private val source = "abcdefghijklmnoprstuwxyz".split("")
    val dictSize = 12

    fun setupGame() {
        word = words.random()
        Log.i("Chosen word", word)
        prepareDictionary()
    }

    private fun prepareDictionary(){
        dict = word.chunked(1).distinct().shuffled() as MutableList<String>
        while (dict.size < dictSize){
            val letter = source.random()
            if (!dict.contains(letter))
                dict.add(letter)
        }
    }
}