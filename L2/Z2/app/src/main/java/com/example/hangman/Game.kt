package com.example.hangman

import android.util.Log

class Game(private val words: Array<String>){
    private var word = ""
    var guessedWord = ""
    lateinit var dict: MutableList<String>
    private val source = "abcdefghijklmnoprstuwxyz".split("").subList(1,25)
    val dictSize = 15
    var chancesLeft = 7

    fun setupGame() {
        word = words.random()
        guessedWord = "?".repeat(word.length)
        Log.i("Chosen word", word)
        prepareDictionary()
    }

    fun updateGuessedWord(character: Char) {
        if (!word.contains(character)){
            chancesLeft -= 1
        }
        word.forEachIndexed { index, c ->
            if (c == character) {
                guessedWord = guessedWord.subSequence(0, index).toString() + c +
                        guessedWord.subSequence(index+1, guessedWord.length).toString()
            }
        }
    }

    fun isFinished(): Boolean{
        return word == guessedWord
    }

    fun isLost(): Boolean{
        return chancesLeft == 0
    }

    private fun prepareDictionary(){
        dict = word.chunked(1).distinct() as MutableList<String>
        while (dict.size < dictSize){
            val letter = source.random()
            if (!dict.contains(letter))
                dict.add(letter)
        }
        dict.shuffle()
    }
}