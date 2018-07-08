package com.example.anton.remindme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;




public class MainActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitToolBar();
    }

    private void InitToolBar(){
        toolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.app_name);

        toolBar.inflateMenu(R.menu.menu);
        //setSupportActionBar(toolBar);

    }
}
