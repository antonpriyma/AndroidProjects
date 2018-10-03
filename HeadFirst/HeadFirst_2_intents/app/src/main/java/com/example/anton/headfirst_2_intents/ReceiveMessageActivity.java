package com.example.anton.headfirst_2_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    public static final String EDIT_TEXT_MESSAGE="message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        Intent intent= getIntent();
        String message = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView messageText=(TextView)findViewById(R.id.message_text);
        messageText.setText(message);
    }
}
