package com.example.anton.chat;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    public final String NAME_EXTRA="NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                            Manifest.permission.INTERNET,
                            },
                    1);
        }

        setContentView(R.layout.activity_login);
        button=findViewById(R.id.next_button);
        editText=findViewById(R.id.name_edit_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.next_button:
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra(NAME_EXTRA,editText.getText().toString());
                        startActivity(intent);
                }
            }
        });


    }
}
