package com.example.tuphanthanh.game_freakingmath;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class InGameActivity extends AppCompatActivity implements View.OnClickListener{
    //time to play each level
    private final int TIME_PLAY_EACH_LEVEL =2000;
    //Define background color of play screen
    private String[] arrColor = {"#FA8072"};

    private TextView tvFomula,tvResult,tvScore;
    private ImageView imvCorrect,imvInCorrect;
    private RelativeLayout layout;
    //timer
    private Timer timer;
    private TimerTask timerTask;
    //player score
    private int score =0;
    //level model
    private LevelModel model;
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove titile bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove nofication bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_in_game);
        tvFomula =findViewById(R.id.tvFomula);
        tvResult =findViewById(R.id.tvResult);
        tvScore = findViewById(R.id.tvScore);

        layout = findViewById(R.id.layoutPlay);

        imvCorrect = findViewById(R.id.imvCorrect);
        imvInCorrect = findViewById(R.id.imvIncorect);

        imvCorrect.setOnClickListener(this);
        imvInCorrect.setOnClickListener(this);
        //Create random
        random = new Random();

//        //generate the first level
        model = GenerateLevel.generateLevel(1);
//        //show level info on screen
        displayNewLevel(model);
//        //create timer,timetask
        createTimeTask();
    }

    private void createTimeTask() {
        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Show gameo over screen
                        showGameover(score);
                    }
                });
            }
        };
        timer.schedule(timerTask,TIME_PLAY_EACH_LEVEL);
    }
    //show popup when game over
    private void showGameover(final int score) {
        //disable button
        imvInCorrect.setEnabled(false);
        imvCorrect.setEnabled(false);
        //cancel timer
        cancelTimer();
        //show gameover UI
        new AlertDialog.Builder(this)
                .setTitle("Game over")
                .setMessage("Your score :" + score)
                .setPositiveButton(R.string.replay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        //enable button
                        imvCorrect.setEnabled(true);
                        imvInCorrect.setEnabled(true);
                        //Play again
                        tvScore.setText("0");
                        InGameActivity.this.score =0;
                        InGameActivity.this.nextLevel(score);

                    }
                })
                .setNegativeButton(R.string.home, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        InGameActivity.this.finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void nextLevel(int score) {
        //update score
        tvScore.setText(String.valueOf(score));
        //cancel timer
        cancelTimer();

        //set new time for next level
        createTimeTask();

        //update ui
        model = GenerateLevel.generateLevel(score);
        displayNewLevel(model);
    }

    private void cancelTimer() {
        timer.cancel();
        timerTask.cancel();
    }

    //display info on screen
    private void displayNewLevel(LevelModel model) {
        tvFomula.setText(model.strOperator);
        tvResult.setText(model.strResult);
        //set random background color
        int indexColor = random.nextInt(arrColor.length);
        layout.setBackgroundColor(Color.parseColor(arrColor[indexColor]));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.imvCorrect){
            if (model.correctWrong == true){
                score+=1;
                nextLevel(score);
            }else{
                showGameover(score);
            }
        }else{
            if (model.correctWrong == false){
                score+=1;
                nextLevel(score);
            }else{
                showGameover(score);
            }
        }
    }
}
