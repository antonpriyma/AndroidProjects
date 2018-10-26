package com.example.anton.rk_1;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {
    private static final int COLUMNS = 3;
    private static final int COLUMNS_LAND = 4;
    private GridAdapter adapter;
    private GridLayoutManager layoutManager;


    public RecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView recyclerView=layout.findViewById(R.id.recyclerView);
        adapter=new GridAdapter();
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
             layoutManager = new GridLayoutManager(getContext(), COLUMNS_LAND);
        }else {
             layoutManager = new GridLayoutManager(getContext(), COLUMNS);
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        return layout;
    }






}
