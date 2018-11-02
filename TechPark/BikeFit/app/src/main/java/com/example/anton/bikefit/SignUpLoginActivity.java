package com.example.anton.bikefit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpLoginActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private  Button signUpGoogleButton;
    private  Button signUpEmailButton;
    private  TextView logInTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up_login);
        signUpGoogleButton = findViewById(R.id.signup_google_button);
        signUpEmailButton = findViewById(R.id.signup_email_button);
        logInTextView=findViewById(R.id.log_in_textView);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.signup_email_button:
                            Intent intentSignUp = new Intent(SignUpLoginActivity.this, SignUpWithEmailActivity.class);
                            startActivity(intentSignUp);
                            break;
                        case R.id.log_in_textView:
                            Intent intentLogIn = new Intent(SignUpLoginActivity.this, LogInActivity.class);
                            startActivity(intentLogIn);
                            break;
                    }

            }
        };


        signUpEmailButton.setOnClickListener(onClickListener);
        signUpGoogleButton.setOnClickListener(onClickListener);
        logInTextView.setOnClickListener(onClickListener);
        ImageView imageView1=(ImageView)findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.cycle_img3).into(imageView1);





    }


}

