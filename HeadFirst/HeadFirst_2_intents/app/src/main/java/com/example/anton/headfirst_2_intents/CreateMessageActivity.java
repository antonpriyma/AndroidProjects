package com.example.anton.headfirst_2_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void OnSendMessage(View view){
        //Intent intent = new Intent(this,ReceiveMessageActivity.class);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        EditText editSend = (EditText)findViewById(R.id.edit_send);
        String sendText=(editSend.getText()).toString();
        intent.putExtra(Intent.EXTRA_TEXT,sendText);
        startActivity(intent);

    }
}
