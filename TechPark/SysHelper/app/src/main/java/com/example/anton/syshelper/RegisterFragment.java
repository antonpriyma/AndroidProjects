package com.example.anton.syshelper;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment {


    private LogInFragment.OnFragmentInteractionListener mListener;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUpButton;
    FragmentTransaction fragmentTransaction;
    LogInFragment logInFragment;
    private FirebaseAuth mAuth;
    private final String AUTH = "AUTH";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        emailEditText = view.findViewById(R.id.input_email);
        passwordEditText = view.findViewById(R.id.input_password);
        signUpButton = view.findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == signUpButton.getId()) {
                    signUp(emailEditText.getText().toString(), passwordEditText.getText().toString());
                }
            }
        };
        signUpButton.setOnClickListener(onClickListener);

    }


    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Register completed.",
                                    Toast.LENGTH_SHORT).show();
                            openLogInFragment();

                        } else {
                            Toast.makeText(getContext(), "Register failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }


    public void openLogInFragment(){
        logInFragment=new LogInFragment();
        fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();

          fragmentTransaction.setCustomAnimations(R.animator.slide_in_left_open,R.animator.slide_in_right_close);
        fragmentTransaction.replace(R.id.fragment_container,logInFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}




