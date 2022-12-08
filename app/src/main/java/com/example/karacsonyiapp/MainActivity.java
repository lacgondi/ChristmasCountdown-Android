package com.example.karacsonyiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView countdown;
    private Timer timer;
    private Date christmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void init(){
        countdown = findViewById(R.id.countdown);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DATE);
        if(month == 11 && day > 24){
            year++;
        }
        Calendar christmas = Calendar.getInstance();
        christmas.set(year, 11,25);
        this.christmas = christmas.getTime();
    }

    @Override
    protected void onStart(){
        super.onStart();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Date now = Calendar.getInstance().getTime();
                long remainingTime = christmas.getTime()-now.getTime();

                long secondInMs = 1000;
                long minInMs = secondInMs*60;
                long hourInMs = minInMs*60;
                long dayInMs = hourInMs*24;

                long day = remainingTime / dayInMs;
                remainingTime%=dayInMs;
                long hour = remainingTime/hourInMs;
                remainingTime%=hourInMs;
                long minutes = remainingTime/minInMs;
                remainingTime%=minInMs;
                long seconds = remainingTime/secondInMs;

                String remainingText = String.format("%d nap %02d:%02d:%02d",day,hour,minutes,seconds);
                runOnUiThread(()->{
                    countdown.setText(remainingText);
                });
            }
        };
        timer.schedule(task, 0,500);
    }

    @Override
    protected void onStop(){
        super.onStop();

    }
}