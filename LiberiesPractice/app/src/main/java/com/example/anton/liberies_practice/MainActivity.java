package com.example.anton.liberies_practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView ageTextView;
    TextView nameTextView;
    TextView weightTextView;
    TextView relationTextView;
    TextView genderTextView;
    ListView listView;
    private ListenerHandler<API.OnUserGetListener> userHandler;

    private API.OnUserGetListener listener=new API.OnUserGetListener() {

        @Override
        public void onUserSuccess(StudentsInfo users) {
            hideKeyboard(editText);
            setUser(users.getUsers());
        }

        @Override
        public void onUserError(Exception error) {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };


    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.get_info_button:
                    if (userHandler != null) {
                        userHandler.unregister();
                    }
                    userHandler=API.getInstance().getUser(editText.getText().toString(), listener);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.number_edit_text);
        button=findViewById(R.id.get_info_button);
        ageTextView=findViewById(R.id.age);
        nameTextView=findViewById(R.id.name);
        weightTextView=findViewById(R.id.weigth);
        relationTextView = findViewById(R.id.relation_status);
        genderTextView =findViewById(R.id.gender);
        listView=findViewById(R.id.items_list);

        button.setOnClickListener(onClickListener);
    }



    private void setUser(ArrayList<UserInfo> users) {
        Collections.sort(users);
        UserListAdapter adapter=new UserListAdapter(this,users);
        listView.setAdapter(adapter);
    }

    private void hideKeyboard(final View input){
        final InputMethodManager inputMethodManager = (InputMethodManager) input.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(input.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (userHandler != null) {
            userHandler.unregister();
        }
    }

}
