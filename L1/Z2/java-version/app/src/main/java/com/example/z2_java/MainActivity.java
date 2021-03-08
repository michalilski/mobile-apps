package com.example.z2_java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private int playerScore;
    private int enemyScore;
    private Map<Integer, String> moveMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame();
    }

    private void startGame(){
        playerScore = 0;
        enemyScore = 0;
        moveMap = new HashMap<>();
        moveMap.put(0, "Rock");
        moveMap.put(1, "Paper");
        moveMap.put(2, "Scissors");
    }

    private int enemyMove(){
        return ThreadLocalRandom.current().nextInt(0, 3);
    }

    @SuppressLint("SetTextI18n")
    private void showResult(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        TextView enemyScoreTextView = (TextView)findViewById(R.id.enemyScoreTextView);
        enemyScoreTextView.setText("Enemy Score: " + enemyScore);
        TextView playerScoreTextView = (TextView)findViewById(R.id.playerScoreTextView);
        playerScoreTextView.setText("Player Score: " + playerScore);
    }

    public void playRock(View view) {
        int enemyMove = enemyMove();
        String moveInfo = "Enemy chose " + moveMap.get(enemyMove) + ". ";
        String resultInfo = "";
        switch (enemyMove){
            case 0:{
                resultInfo = "Draw.";
                break;
            }
            case 1:{
                resultInfo = "You lost.";
                enemyScore ++;
                break;
            }
            case 2:{
                resultInfo = "You won.";
                playerScore ++;
                break;
            }
        }
        showResult(moveInfo + resultInfo);
    }

    public void playPaper(View view) {
        int enemyMove = enemyMove();
        String moveInfo = "Enemy chose " + moveMap.get(enemyMove) + ". ";
        String resultInfo = "";
        switch (enemyMove){
            case 0:{
                resultInfo = "You won.";
                playerScore ++;
                break;
            }
            case 1:{
                resultInfo = "Draw.";
                break;
            }
            case 2:{
                resultInfo = "You lost.";
                enemyScore ++;
                break;
            }
        }
        showResult(moveInfo + resultInfo);
    }

    public void playScissors(View view) {
        int enemyMove = enemyMove();
        String moveInfo = "Enemy chose " + moveMap.get(enemyMove) + ". ";
        String resultInfo = "";
        switch (enemyMove){
            case 0:{
                resultInfo = "You lost.";
                enemyScore ++;
                break;
            }
            case 1:{
                resultInfo = "You won.";
                playerScore ++;
                break;
            }
            case 2:{
                resultInfo = "Draw.";
                break;
            }
        }
        showResult(moveInfo + resultInfo);
    }
}