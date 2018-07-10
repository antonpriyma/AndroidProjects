package com.example.anton.remindme;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;




public class MainActivity extends AppCompatActivity {

    private static final int Layout=R.layout.activity_main;
    private android.support.v7.widget.Toolbar toolBar;
    private DrawerLayout drawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(Layout);
        InitToolBar();
        InitNavigationView();
    }

    private void InitToolBar(){
        toolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.app_name);

        toolBar.inflateMenu(R.menu.menu);
        //setSupportActionBar(toolBar);

    }

    private void InitNavigationView() {
        drawerLayout=(DrawerLayout)findViewById(R.id.activity_main);

    }

}
