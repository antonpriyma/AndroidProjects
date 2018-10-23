package com.example.anton.rk_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NumberActivity extends AppCompatActivity {
    public static final String EXTRA_NUMBER="number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        NumberFragment numberFragment=(NumberFragment)getSupportFragmentManager().findFragmentById(R.id.list_flag);
        int number = getIntent().getIntExtra(EXTRA_NUMBER,-1);
        numberFragment.setNumber(number);

    }
}
