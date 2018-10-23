package com.example.anton.lecture3_task;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String BROADCAST_ACTION ="OPEN" ;

    private EditText value;
    private TextView textView;
    private BroadcastReceiver br =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(SaveService.EXTRA_VALUE);
            if (data!=null) {
                value.setText(getString(R.string.placeholder, data));
            }else {
                Toast toast=Toast.makeText(getApplicationContext(),"NUll",Toast.LENGTH_LONG);
                toast.show();
            }

        }
    };;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value= findViewById(R.id.save_text);
        textView= findViewById(R.id.result_text);


        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveValue();
            }
        });

        findViewById(R.id.open_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openValue();
            }
        });

        IntentFilter intFilt = new IntentFilter();
        intFilt.addAction(BROADCAST_ACTION);
        registerReceiver(br,intFilt);

    }

    private void saveValue() {
        String data=value.getText().toString();
        Intent intent = new Intent(this,SaveService.class);
        intent.setAction(SaveService.ACTION_SAVE);
        intent.putExtra(SaveService.EXTRA_VALUE,data);
        startService(intent);
    }

    private void openValue() {
        Intent intent = new Intent(this,SaveService.class);
        intent.setAction(SaveService.ACTION_OPEN);
        startService(intent);
    }


}
