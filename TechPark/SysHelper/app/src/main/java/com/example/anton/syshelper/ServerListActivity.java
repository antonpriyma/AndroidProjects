package com.example.anton.syshelper;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListActivity extends AppCompatActivity {
    private final String USERS_TABLE="users";

    private ArrayList<ServerInfo> data;
    RecyclerView rv;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference myRef = database.getReference(USERS_TABLE);
    private android.support.v7.widget.Toolbar toolbar;


    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);

        toolbar=findViewById(R.id.servers_toolbar);
        toolbar.setTitle("Servers");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorSecondary),PorterDuff.Mode.SRC_ATOP);
        rv=findViewById(R.id.servers_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        data=new ArrayList<>();
        data.add(new ServerInfo("Server 1","lab.posevin.com",":22","asdfasdf","Server 1"));
        rv.setAdapter(new ServerListAdapter(data));
        getMyRef().child(firebaseUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ServerInfo info =dataSnapshot.getValue(ServerInfo.class);
                data.add(info);
                rv.getAdapter().notifyDataSetChanged();

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.server_list_toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Executor executor=Executors.newCachedThreadPool();
        ((ExecutorService) executor).submit(new Runnable() {
            @Override
            public void run() {
                AddTask("test","test","test","test","test");
            }
        });

        return true;
    }

    private void AddTask(String name, String host, String port, String password, String title){
        myRef.child(firebaseUser.getUid()).push().setValue(new ServerInfo(name,host,port,password,title));
    }

    public DatabaseReference getMyRef() {
        return myRef;
    }
}
