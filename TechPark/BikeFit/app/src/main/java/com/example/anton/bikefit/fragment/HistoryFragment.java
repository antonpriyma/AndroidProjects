package com.example.anton.bikefit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anton.bikefit.R;
import com.example.anton.bikefit.dto.RemindDTO;
import com.example.anton.bikefit.dto.RemindListAdapter;

import java.util.List;

public class HistoryFragment extends AbstractFragment {
    private static final int LAYOUT=R.layout.fragment_history;
    private LinearLayoutManager mLayoutManager;
    List<RemindDTO> data;


    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<RemindDTO> data) {
        this.data = data;
    }

    public static HistoryFragment getInstance(Context context, List<RemindDTO> data) {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(data);
        fragment.setTitle(context.getString(R.string.history_string));

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView rv =(RecyclerView)view.findViewById(R.id.recycle_view);

        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new RemindListAdapter(createMockData()));
        return view;
    }

    private List<RemindDTO> createMockData() {
        data.add(new RemindDTO("Демас гей"));
        data.add(new RemindDTO("Демас гей"));
        data.add(new RemindDTO("Item 1"));
        data.add(new RemindDTO("Item 1"));
        data.add(new RemindDTO("Item 1"));
        return data;
    }
}
