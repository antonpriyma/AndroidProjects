package com.example.anton.bikefit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button logInButton;
    private final String TAG="LogInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email=(EditText)findViewById(R.id.email_login_edit_text) ;
        password=(EditText)findViewById(R.id.password_login_edit_text) ;
        //logInButton=(Button)findViewById(R.id.log_in_button);
        mAuth = FirebaseAuth.getInstance();
        /*
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==logInButton.getId()){
                    logIn(email.getText().toString(),password.getText().toString());
                }
            }
        };
        logInButton.setOnClickListener(onClickListener);
        */

        Toolbar toolbar= findViewById(R.id.toolbar_log_in);
        toolbar.setTitle(R.string.log_in_string);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void logIn(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LogInActivity.this, "Authentication completed.",
                                    Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),MainRemindActivity.class);
                            startActivity(intent);
                            Log.d(TAG,"Authentication completed.");
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            Log.d(TAG,"Authentication failed.");

                        }

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_in_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.log_in_action){
            logIn(email.getText().toString(),password.getText().toString());
        }else if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
