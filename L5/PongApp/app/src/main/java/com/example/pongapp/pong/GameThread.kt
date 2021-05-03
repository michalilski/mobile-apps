package com.example.pongapp.pong

class GameThread(private val gameLogic: GameLogic, private val pongActivity: PongActivity)
    : Thread() {

    var running = false
    private var targetFPS = 25

    override fun run() {
        var startTime : Long
        var timeMillis : Long
        var waitTime: Long
        val targetTime = (1000/targetFPS).toLong()

        while (running) {
            startTime = System.nanoTime()
            gameLogic.update()
            pongActivity.draw()
            if(gameLogic.finished) {
                pongActivity.notifyGameFinished()
                running = false
            }
            timeMillis = (System.nanoTime() - startTime)/ 1000000
            waitTime = targetTime - timeMillis

            if (waitTime >= 0)
                sleep(waitTime)
        }
    }
}