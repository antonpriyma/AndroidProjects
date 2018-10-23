package com.example.anton.headfirst71_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {
    static interface WorkoutListListener {
        void itemClicked(long id);
    }
    private WorkoutListListener listener;


    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Workout.getWorkouts().length];
        int i=0;
        for(Workout w: Workout.getWorkouts()){
            names[i++]=w.getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),android.R.layout.simple_list_item_1,names
        );
        setListAdapter(adapter);

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener=(WorkoutListListener)context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener!=null){
            listener.itemClicked(id);
        }
    }
}
