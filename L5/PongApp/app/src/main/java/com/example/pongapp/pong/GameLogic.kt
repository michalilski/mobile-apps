package com.example.pongapp.pong

class GameLogic(val pongActivity: PongActivity) {
    lateinit var ball: Ball
    lateinit var playerOne: Player
    lateinit var playerTwo: Player
    var width = -1
    var height = -1
    private var dxBall = (GameConfig.speed / 2) * listOf(-1, 1).random()
    private var dyBall = GameConfig.speed
    var finished = false

    fun update() {

        if (ball.xPos <= 0 || ball.xPos + ball.size >= width)
            dxBall *= -1

        if (ball.yPos <= playerOne.yPos + playerOne.ySize
                && ball.yPos >= playerOne.yPos - playerOne.ySize) {

            if (ball.xPos <= playerOne.xPos + playerOne.xSize
                    && ball.xPos >= playerOne.xPos - playerOne.xSize)
                dyBall *= -1
            else {
                playerTwo.score += 1
                reset()
            }
        }

        if (ball.yPos >= playerTwo.yPos - playerTwo.ySize
                && ball.yPos <= playerTwo.yPos + playerTwo.ySize) {

            if (ball.xPos <= playerTwo.xPos + playerTwo.xSize
                    && ball.xPos >= playerTwo.xPos - playerTwo.xSize)
                dyBall *= -1
            else {
                playerOne.score += 1
                reset()
            }
        }

        ball.xPos += dxBall
        ball.yPos += dyBall
    }

    private fun reset() {
        resetBall()
        if (playerOne.score == GameConfig.scoreLimit || playerTwo.score == GameConfig.scoreLimit) {
            dxBall = 0
            dyBall = 0
            finished = true
        }
    }

    private fun resetBall() {
        ball.xPos = width / 2
        ball.yPos = height / 4
        dxBall = (GameConfig.speed / 2) * listOf(-1, 1).random()
        dyBall = GameConfig.speed
    }
}