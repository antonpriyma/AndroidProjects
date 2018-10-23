package com.example.anton.headfirst71_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkoutDetail extends Fragment {
    private long workoutId;

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState!=null){
            workoutId=savedInstanceState.getLong("workoutId");
        }
        FragmentTransaction ft =getChildFragmentManager().beginTransaction();
        StopWatchFragment stopWatchFragment=new StopWatchFragment();
        ft.replace(R.id.stopwatch_container,stopWatchFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }



    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view!=null){
            TextView title=(TextView)view.findViewById(R.id.titleText);
            Workout workout=Workout.getWorkouts()[(int)workoutId];
            title.setText(workout.getName());
            TextView description=(TextView)view.findViewById(R.id.textDiscription);
            description.setText(workout.getDecryption());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong("workoutId",workoutId);
    }
}
