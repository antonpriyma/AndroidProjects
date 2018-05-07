package com.example.anton.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SettingsActivity extends AppCompatActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        layout = (LinearLayout)findViewById(R.id.settingsactivity);
        layout.setBackgroundColor(getColor(R.color.colorback));

    }
}
