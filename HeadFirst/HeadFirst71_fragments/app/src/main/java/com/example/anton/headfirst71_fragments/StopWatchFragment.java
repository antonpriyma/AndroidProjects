package com.example.anton.headfirst71_fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class StopWatchFragment extends Fragment implements View.OnClickListener{

    private boolean running;
    private boolean wasRunning;
    private int seconds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seconds=0;
        running=false;
        wasRunning=false;
        if (savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
    }

    private void runTimer(View view){
        final TextView secondSize = (TextView)view.findViewById(R.id.timer_text);
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


    public StopWatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(layout);
        Button startButton = (Button)layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button)layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button)layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return layout;

    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    public void onPause(){
        super.onPause();
        wasRunning=running;
        running=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        running=wasRunning;
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_button:
                running=true;
                break;
            case R.id.stop_button:
                running=false;
                break;
            case R.id.reset_button:
                running=false;
                seconds=0;
        }
    }
}
