package com.miguel.app.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    int counter;
    ProgressBar progressBar;
    TextView txtProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        counter = 0;

        progressBar = findViewById(R.id.custom_progress_bar);
        txtProgress = findViewById(R.id.txtProgress);

        progressBar.setVisibility(View.VISIBLE);
        //
        // progressBar.setTrackThickness(36);
        // progressBar.setIndicatorColor(Color.GREEN);
        // progressBar.setTrackCornerRadius(16);
        progressBar.setProgress(0);
        progressBar.setMax(30);
        // startTimerGame();
        startAnimation();
    }

    public void startTimerGame() {
        final Timer timer = new Timer();
        Handler handler = new Handler();

        Runnable runnable = () -> {
            counter += 1;
            progressBar.setProgress(counter);
            txtProgress.setText(counter + " seconds");
            if (counter >= 30)
                timer.cancel();
            Log.i("MITO_DEBUG", "in progress");
        };

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 0, 1000);

    }

    private void startAnimation() {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        progressAnimator.setDuration(30000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }
}