package com.example.anton.bikefit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

//import com.example.anton.remindme.adapter.TabsPagerFragmentAdapter;
//import com.example.anton.remindme.dto.RemindDTO;
//import com.example.anton.remindme.fragment.AbstractFragment;
//import com.example.anton.remindme.fragment.HistoryFragment;
//import com.example.anton.remindme.fragment.IdeasFragment;

import com.example.anton.bikefit.adapter.TabsPagerFragmentAdapter;
import com.example.anton.bikefit.dto.RemindDTO;
import com.example.anton.bikefit.fragment.ToDoFragment;
import com.example.anton.bikefit.fragment.ToDoFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainRemindActivity extends AppCompatActivity {

    private final String USERS_TABLE="users";

    private static final int Layout=R.layout.activity_main_remind;
    private android.support.v7.widget.Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;
    private LinearLayout bottomSheet;
    private EditText taskAddText;
    private BottomSheetBehavior bottomSheetBehavior;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference myRef = database.getReference(USERS_TABLE);
    ToDoFragment toDoFragment;
    int number;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.plus_button:
                    if(bottomSheetBehavior.getState()==BottomSheetBehavior.STATE_HIDDEN) {
                        floatingActionButton.setImageResource(R.drawable.baseline_create_black_24);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }else {
                        hideKeyboard();
                        AddTask(taskAddText.getText().toString());
                        floatingActionButton.setImageResource(R.drawable.plus);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                    break;
                }
            }
        };

    public DatabaseReference getMyRef() {
        return myRef;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Layout);
        InitToolBar();
        initTabs();
        taskAddText=findViewById(R.id.task_input_edit_text);
        bottomSheet=findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        number=0;
        getMyRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ArrayList<RemindDTO> list = dataSnapshot.child(firebaseUser.getUid()).getValue(ArrayList.class);
//                if (list != null){
//                    historyFragment.getData().add(list.get(0));
//                    historyFragment.getRv().getAdapter().notifyDataSetChanged();
//                }
//
//                historyFragment.getRv().getAdapter().notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        getMyRef().child(firebaseUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                RemindDTO dto=dataSnapshot.getValue(RemindDTO.class);
                toDoFragment.getData().add(dto);
                toDoFragment.getRv().getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setTheme(R.style.AppThemeNoActionBar);
        InitNavigationView();
        InitActionButton();
    }

    private void InitToolBar(){
        toolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.app_name);

        setSupportActionBar(toolBar);

    }




    private void InitActionButton(){
        floatingActionButton=(FloatingActionButton)findViewById(R.id.plus_button);
        floatingActionButton.setBackgroundColor(Color.parseColor("#1976D2"));
        floatingActionButton.setOnClickListener(onClickListener);
    }//разобраться

    private void AddTask(String s){
        myRef.child(firebaseUser.getUid()).push().setValue(new RemindDTO(s));
    }


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

             toDoFragment=ToDoFragment.getInstance(this,new ArrayList<RemindDTO>());
             viewPager = (ViewPager)findViewById(R.id.view_pager);//Разметка
         TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);//Разметка Бара выбора
           TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(this,getSupportFragmentManager());//Обработчик выбора
          adapter.addFragment(toDoFragment,getString(R.string.todo_string));//Example fragment - просто xml разметка
//        adapter.addFragment(new AbstractFragment(),getString(R.string.todo_string));
//        adapter.addFragment(new AbstractFragment(),getString(R.string.ideas_string));
//        adapter.addFragment(new AbstractFragment(),getString(R.string.birthday_string));
         viewPager.setAdapter(adapter);
          tabLayout.setupWithViewPager(viewPager);
  }

    private void ShowHistoryTab(){
        viewPager.setCurrentItem(CONSTANTS.TAB_ONE);
    }

    public void hideKeyboard() {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
