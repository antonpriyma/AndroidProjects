package com.example.anton.headfirst_6_lists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        ListView listView=(ListView)findViewById(R.id.list_menu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                if (position==0){
                    Intent intent = new Intent(TopLevelActivity.this,DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
