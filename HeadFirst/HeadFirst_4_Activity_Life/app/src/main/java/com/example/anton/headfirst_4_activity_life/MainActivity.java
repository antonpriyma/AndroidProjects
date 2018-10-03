package com.example.anton.headfirst_4_activity_life;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private boolean wasRunning;
    private int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seconds=0;
        running=false;
        wasRunning=false;
        if (savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    public void OnClickStart(View view){
        running=true;
    }


    public void OnClickStop(View view){
        running=false;
    }


    public void OnClickReset(View view){
        running=false;
        seconds=0;

    }

    private void runTimer(){
        final TextView secondSize = (TextView)findViewById(R.id.timer_text);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs=seconds%60;
                String time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
                secondSize.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning=running;
        running=false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        running=wasRunning;
    }
}
