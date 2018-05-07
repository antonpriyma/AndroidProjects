package com.example.anton.myapplication;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.anton.myapplication.R.id.Textclick;
import static com.example.anton.myapplication.R.id.action_blue;
import static com.example.anton.myapplication.R.id.button_click;
import static com.example.anton.myapplication.R.id.button_click1;
import static com.example.anton.myapplication.R.id.button_click2;
import static com.example.anton.myapplication.R.id.button_click3;
import static com.example.anton.myapplication.R.id.cancel_action;
import static com.example.anton.myapplication.R.id.sampleLayout;

public class LaunchActivity extends AppCompatActivity {
    private Button mBtnClick,mBtnClick1,mBtnClick2,mBtnClick3;
    private TextView mText;
    private int trigger=0;
    private LinearLayout CurLayout;

    public void showToast(View view){
        String s = new String();
        s = "Нажата кнопка" + view.getId();
        Toast toast = Toast.makeText(getApplicationContext(),
                s ,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.testmenu,menu)
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_blue:
                CurLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.action_white:
                CurLayout.setBackgroundColor(Color.WHITE);
                break;
            case R.id.action_settings:
                Toast.makeText(this,"You picked settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        final String newString = getString(R.string.new_text);
        mBtnClick = (Button)findViewById(button_click);
        mBtnClick1=(Button)findViewById(button_click1);
        mBtnClick2 = (Button)findViewById(button_click2);
        mBtnClick3=(Button)findViewById(button_click3);
        TextView textclick = (TextView)findViewById(Textclick);
        CurLayout  = (LinearLayout)findViewById(R.id.sampleLayout);

        textclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurLayout.setBackgroundColor(Color.GRAY);
            }
        });

        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_click:
                        CurLayout.setBackgroundColor(Color.RED);
                        showToast(view);
                        break;

                    case R.id.button_click1:
                        CurLayout.setBackgroundColor(Color.GREEN);
                        break;

                    case R.id.button_click2:
                        CurLayout.setBackgroundColor(Color.BLACK);
                        break;

                    case R.id.button_click3:
                        CurLayout.setBackgroundColor(Color.YELLOW);
                        break;


                }
            }
        };
        mBtnClick.setOnClickListener(Listener);
        mBtnClick1.setOnClickListener(Listener);
        mBtnClick2.setOnClickListener(Listener);
        mBtnClick3.setOnClickListener(Listener);

    }
}
