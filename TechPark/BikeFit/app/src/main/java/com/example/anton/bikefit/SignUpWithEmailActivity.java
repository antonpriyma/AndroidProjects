package com.example.anton.bikefit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpWithEmailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button registerButton;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_email);
        mAuth = FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.email_register_edit_text) ;
        password=(EditText)findViewById(R.id.password_register_edit_text) ;
        registerButton=(Button)findViewById(R.id.register_button);
        frameLayout=(FrameLayout)findViewById(R.id.frame_layout);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==registerButton.getId()){
                    createAccount(email.getText().toString(),password.getText().toString());
                }
            }
        };
        registerButton.setOnClickListener(onClickListener);
        android.support.v7.widget.Toolbar toolbar= findViewById(R.id.toolbar_sign_up);
        toolbar.setTitle(R.string.sign_up_string);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpWithEmailActivity.this, "Authentication completed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpWithEmailActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
