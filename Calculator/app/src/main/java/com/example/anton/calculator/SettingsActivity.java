package com.example.anton.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class SettingsActivity extends AppCompatActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        layout = (LinearLayout)findViewById(R.id.settingsactivity);
        layout.setBackgroundColor(getColor(R.color.colorback));
        View.OnClickListener OnClockListener=new View.OnClickListener(){

            @Override
            public void onClick(View view) {
             switch (view.getId()){
                 case R.id.red_settings_button:

             }
            }
        };

    }
}
