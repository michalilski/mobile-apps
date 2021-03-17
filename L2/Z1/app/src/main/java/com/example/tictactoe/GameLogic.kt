package com.example.tictactoe

class GameLogic(private val gameSize: Int) {
    val currentPlayerSymbol = hashMapOf(1 to "O", -1 to "X")
    var currentPlayer = 1
    val gameBoard = mutableListOf<MutableList<Int>>()
    var gameFinished = false

    fun setupGame() {
        setupBoard()
    }


    private fun setupBoard() {
        for (i in 0 until gameSize) {
            gameBoard.add(mutableListOf())
            for (j in 0 until gameSize) {
                gameBoard[i].add(0)
            }
        }
    }

    fun checkResult(): Boolean {
        return checkRows() || checkColumns() || checkDiagonals()
    }

    private fun checkRows(): Boolean {
        for (i in 0 until gameSize) {
            val first = gameBoard[i][0]
            if (first != 0) {
                var equal = true
                for (j in 1 until gameSize) {
                    equal = gameBoard[i][j] == first && equal
                }
                if (equal)
                    return true
            }
        }
        return false
    }

    private fun checkColumns(): Boolean {
        for (i in 0 until gameSize) {
            val first = gameBoard[0][i]
            if (first != 0) {
                var equal = true
                for (j in 1 until gameSize) {
                    equal = gameBoard[j][i] == first && equal
                }
                if (equal)
                    return true
            }
        }
        return false
    }

    private fun checkDiagonals(): Boolean {
        var first = gameBoard[0][0]
        if (first != 0) {
            var equal = true
            for (i in 1 until gameSize) {
                equal = gameBoard[i][i] == first && equal
            }
            if (equal)
                return true
        }

        first = gameBoard[0][gameSize - 1]
        if (first != 0) {
            var equal = true
            for (i in 1 until gameSize) {
                equal = gameBoard[i][gameSize - 1 - i] == first && equal
            }
            if (equal)
                return true
        }
        return false
    }
}