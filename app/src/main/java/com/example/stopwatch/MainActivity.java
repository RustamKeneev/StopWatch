package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView timer_text_view;

    private int seconds = 0;
    private boolean isRunning = false;
    private boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAndBuildViews();
        if (savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        wasRunning = isRunning;
//        isRunning = false;
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        isRunning = wasRunning;
//    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = wasRunning;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("isRunning",isRunning);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void initAndBuildViews() {
        timer_text_view = findViewById(R.id.timer_text_view);
    }

    public void buttonOnclickStartTimer(View view) {
        isRunning = true;
    }

    public void buttonOnclickStopTimer(View view) {
        isRunning = false;
    }

    public void buttonOnclickRemoveTimer(View view) {
        isRunning = false;
        seconds = 0;
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minute = (seconds % 3600) / 60;
                int seccondsSumm = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d",hours,minute,seccondsSumm);
                timer_text_view.setText(time);
                if (isRunning){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }
}
