package com.example.pongapp.pong

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pongapp.MainActivity
import com.example.pongapp.R
import org.w3c.dom.Text
import java.sql.Timestamp
import java.time.Instant
import java.util.*

class PongActivity : AppCompatActivity(), SurfaceHolder.Callback{
    val gameLogic = GameLogic(this)
    val playerPaint = Paint()
    val ballPaint = Paint()
    val gameThread = GameThread(gameLogic, this)
    lateinit var scoreOneTextView : TextView
    lateinit var scoreTwoTextView : TextView
    private lateinit var surfaceView : SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pong)
        supportActionBar?.hide()
        surfaceView = findViewById(R.id.pongSurfaceView)
        surfaceView.holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        setupGame()
        setupPaints()
        setupButtons()
        gameThread.running = true
        gameThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        draw()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder){
        gameThread.running = false
        gameThread.join()
    }

    fun draw(){
        scoreOneTextView.text = gameLogic.playerOne.score.toString()
        scoreTwoTextView.text = gameLogic.playerTwo.score.toString()
        val canvas = surfaceView.holder.lockCanvas()
        if(canvas != null){
            canvas.drawARGB(255,0,0,0)
            drawBall(canvas, gameLogic.ball)
            drawPlayer(canvas, gameLogic.playerOne)
            drawPlayer(canvas, gameLogic.playerTwo)
            surfaceView.holder.unlockCanvasAndPost(canvas)
        }
    }

    fun notifyGameFinished() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("lastGame", GameRecord(
            Timestamp.from(Instant.now()),
            gameLogic.playerOne.score,
            gameLogic.playerTwo.score
        ))
        startActivityIfNeeded(intent, 0)
        finish()
    }

    private fun setupGame() {
        val canvas = surfaceView.holder.lockCanvas()
        gameLogic.ball = Ball(
            canvas.width/2,
            canvas.height/4,
            GameConfig.ballSize
        )
        gameLogic.playerOne = Player(
            canvas.width/2,
            GameConfig.playerSizeX/2,
            GameConfig.playerSizeX,
            GameConfig.playerSizeY,
            0
        )
        gameLogic.playerTwo = Player(
            canvas.width/2,
            canvas.height - GameConfig.playerSizeX/2,
            GameConfig.playerSizeX,
            GameConfig.playerSizeY,
            0
        )
        gameLogic.width = canvas.width
        gameLogic.height = canvas.height
        surfaceView.holder.unlockCanvasAndPost(canvas)
    }

    private fun drawBall(canvas: Canvas, ball: Ball) {
        canvas.drawOval(
            (ball.xPos-(ball.size/2)).toFloat(),
            (ball.yPos-(ball.size/2)).toFloat(),
            (ball.xPos+(ball.size/2)).toFloat(),
            (ball.yPos+(ball.size/2)).toFloat(),
            ballPaint
        )
    }

    private fun drawPlayer(canvas: Canvas, player: Player) {
        canvas.drawRect(
            (player.xPos-(player.xSize/2)).toFloat(),
            (player.yPos-(player.ySize/2)).toFloat(),
            (player.xPos+(player.xSize/2)).toFloat(),
            (player.yPos+(player.ySize/2)).toFloat(),
            playerPaint
        )
    }

    private fun setupPaints(){
        playerPaint.style = Paint.Style.FILL
        playerPaint.color = Color.CYAN

        ballPaint.style = Paint.Style.FILL
        ballPaint.color = Color.WHITE
    }

    private fun setupButtons() {
        findViewById<ImageButton>(R.id.topLeftButton).setOnClickListener {
            if (gameLogic.playerOne.xPos > GameConfig.playerSizeX)
                gameLogic.playerOne.xPos -= GameConfig.playerStep
        }
        findViewById<ImageButton>(R.id.topRightButton).setOnClickListener {
            if (gameLogic.playerOne.xPos < gameLogic.width - GameConfig.playerSizeX)
            gameLogic.playerOne.xPos += GameConfig.playerStep
        }
        findViewById<ImageButton>(R.id.bottomLeftButton).setOnClickListener {
            if (gameLogic.playerTwo.xPos > GameConfig.playerSizeX)
                gameLogic.playerTwo.xPos -= GameConfig.playerStep
        }
        findViewById<ImageButton>(R.id.bottomRightButton).setOnClickListener {
            if (gameLogic.playerTwo.xPos < gameLogic.width - GameConfig.playerSizeX)
                gameLogic.playerTwo.xPos += GameConfig.playerStep
        }
        scoreOneTextView = findViewById(R.id.scoreOneTextView)
        scoreTwoTextView = findViewById(R.id.scoreTwoTextView)
    }
}