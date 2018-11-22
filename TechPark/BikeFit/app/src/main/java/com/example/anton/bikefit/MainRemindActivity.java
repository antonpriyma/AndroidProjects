package com.example.anton.bikefit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

//import com.example.anton.remindme.adapter.TabsPagerFragmentAdapter;
//import com.example.anton.remindme.dto.RemindDTO;
//import com.example.anton.remindme.fragment.AbstractFragment;
//import com.example.anton.remindme.fragment.HistoryFragment;
//import com.example.anton.remindme.fragment.IdeasFragment;

import com.example.anton.bikefit.adapter.TabsPagerFragmentAdapter;
import com.example.anton.bikefit.dto.RemindDTO;
import com.example.anton.bikefit.fragment.HistoryFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainRemindActivity extends AppCompatActivity {

    private static final int Layout=R.layout.activity_main_remind;
    private android.support.v7.widget.Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    //myRef.setValue("Hello, World!");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Layout);
        InitToolBar();
        initTabs();
        setTheme(R.style.AppThemeNoActionBar);
        InitNavigationView();

    }

    private void InitToolBar(){
        toolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.app_name);

        setSupportActionBar(toolBar);

    }


    private void InitActionButton(){
        floatingActionButton=(FloatingActionButton)findViewById(R.id.plus_button);
        floatingActionButton.setBackgroundColor(Color.parseColor("#1976D2"));
    }//разобраться


    private void InitNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        //Кнопка вызова NavigationView
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open_string,R.string.close_string);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.actionNotificationItem:
                        ShowHistoryTab();
                }
                return true;
            }
        });//Триггер по кнопкам


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

      private void initTabs() {//Инициализация верхнего меню
           HistoryFragment historyFragment;
             historyFragment=HistoryFragment.getInstance(this,new ArrayList<RemindDTO>());
             viewPager = (ViewPager)findViewById(R.id.view_pager);//Разметка
         TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);//Разметка Бара выбора
           TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(this,getSupportFragmentManager());//Обработчик выбора
          adapter.addFragment(historyFragment,getString(R.string.history_string));//Example fragment - просто xml разметка
//        adapter.addFragment(new AbstractFragment(),getString(R.string.todo_string));
//        adapter.addFragment(new AbstractFragment(),getString(R.string.ideas_string));
//        adapter.addFragment(new AbstractFragment(),getString(R.string.birthday_string));
         viewPager.setAdapter(adapter);
          tabLayout.setupWithViewPager(viewPager);
  }

    private void ShowHistoryTab(){
        viewPager.setCurrentItem(CONSTANTS.TAB_ONE);
    }
}
