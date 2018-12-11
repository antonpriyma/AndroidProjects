package com.example.anton.syshelper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.jraska.console.Console;

public class ConsoleFragment extends AbstractFragment{
    private static final int LAYOUT=R.layout.fragment_console;
    private LinearLayoutManager mLayoutManager;
    private TextView diskUsageProcent;
    private TextView diskUsage;
    private TextView diskSpace;
    private TextView diskName;
    CircularProgressBar progressBar;
    private final String DELIM ="/";


    public void setContext(Context context) {
        this.context = context;
    }

    //public void setData(List<RemindDTO> data) {
    // this.data = data;
    //}

    public static ConsoleFragment getInstance(Context context) {
        Bundle args = new Bundle();
        ConsoleFragment fragment=new ConsoleFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle("TERMINAL");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        //Console console=view.findViewById(R.id.console);
//        Console console=new Console();
        Console.write("Test");
    }

    @Override
    public void onStart() {
        super.onStart();

    }


}
