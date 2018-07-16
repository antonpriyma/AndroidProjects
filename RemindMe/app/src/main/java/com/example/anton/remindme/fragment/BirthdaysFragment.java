package com.example.anton.remindme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anton.remindme.R;

public class BirthdaysFragment extends Fragment{

    private static final int LAYOUT=R.layout.fragment_example;

    /*
    public static void setTITLE(String TITLE) {
        BirthdaysFragment.TITLE = TITLE;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT, container, false);
    }
}
