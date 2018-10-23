package com.example.anton.headfirst71_fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetail workoutDetail =(WorkoutDetail)getSupportFragmentManager().findFragmentById(R.id.detail_flag);
        int workoutId=(int)getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        workoutDetail.setWorkoutId(workoutId);
    }
}
