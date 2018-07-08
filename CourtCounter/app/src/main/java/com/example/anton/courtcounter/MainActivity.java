package com.example.anton.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private int teamAScore=0;
    private int teamBScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void increaceTeamAScore3(View view){
        teamAScore+=3;
        displayTeamAScore();
    }

    protected void increaceTeamAScore2(View view){
        teamAScore+=2;
        displayTeamAScore();
    }

    private void displayTeamAScore(){
        TextView scoreA= (TextView)findViewById(R.id.team_a_score);
        scoreA.setText(String.valueOf(teamAScore));
    }

    protected void increaceTeamBScore3(View view){
        teamBScore+=3;
        displayTeamBScore();
    }

    protected void increaceTeamBScore2(View view){
        teamBScore+=2;
        displayTeamBScore();
    }

    private void displayTeamBScore(){
        TextView scoreB= (TextView)findViewById(R.id.team_b_score);
        scoreB.setText(String.valueOf(teamBScore));
    }


    protected void resetScores(View view){
        teamAScore=0;
        displayTeamAScore();
        teamBScore=0;
        displayTeamBScore();
    }


}
