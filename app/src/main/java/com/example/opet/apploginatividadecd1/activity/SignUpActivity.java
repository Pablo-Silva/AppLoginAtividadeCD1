package com.example.opet.apploginatividadecd1.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.opet.apploginatividadecd1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText editLogin, editPassword, editPassword2;
    private TextView textStatus;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);
        editPassword2 = findViewById(R.id.editPassword2);
        textStatus = findViewById(R.id.textStatus);
        mAuth = FirebaseAuth.getInstance();
    }

    public void singUp(View view) {

        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();
        String password2 = editPassword2.getText().toString();

        if(password.length() >= 8 && password2.length() >= 8){

            if (!password.equals(password2)) {
                textStatus.setVisibility(View.VISIBLE);
                textStatus.setText("Passwords do not match.");
            } else {
                textStatus.setVisibility(View.VISIBLE);
                textStatus.setText("Loading...");
                mAuth.createUserWithEmailAndPassword(login, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        textStatus.setText("User created successfully! Redirecting...");
                        dashRedirect();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        textStatus.setText("Could not create user.");
                    }
                });
            }

        } else{
            textStatus.setText("minimum 8 characters for the password.");
        }


    }

    void dashRedirect() {
        Intent newActivity = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(newActivity);
    }

    public void backMain(View view){
        mainRedirect();
    }

    void mainRedirect(){
        Intent newActivity = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(newActivity);
    }
}

