package com.example.anton.rk_1;


import android.content.Intent;
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
    private static final int MIN_COLUMNS = 3;
    private GridAdapter adapter;


    public RecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView recyclerView=layout.findViewById(R.id.recyclerView);
        final int columns=3;
        List<Item> items=buildItemList(columns);
        adapter=new GridAdapter(items);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), columns);
        //layoutManager.setSpanSizeLookup(spanSizeLookup);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(layoutManager);

        return layout;
    }

    public List<Item> buildItemList(int columns) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {

            items.add(new Item(i));
        }

        return items;
    }





}
