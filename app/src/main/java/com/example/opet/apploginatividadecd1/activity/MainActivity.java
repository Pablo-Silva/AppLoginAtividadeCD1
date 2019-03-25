package com.example.opet.apploginatividadecd1.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opet.apploginatividadecd1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText editLogin, editPassword;
    private TextView textMessage;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);
        textMessage = findViewById(R.id.textMessage);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    void updateUI(FirebaseUser currentUser){
        if(currentUser != null){
            dashRedirect();
        }
        else{
            Toast.makeText(this, "User is offline.", Toast.LENGTH_SHORT).show();
        }
    }

    public void signIn(View view) {
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();
        textMessage.setVisibility(View.VISIBLE);
        textMessage.setText("Looking for user");
        mAuth.signInWithEmailAndPassword(login,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                textMessage.setVisibility(View.GONE);
                updateUI(mAuth.getCurrentUser());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                textMessage.setText("Login/Password do not confer.");
            }
        });
    }

    public void signUp(View view) {
        signUpRedirect();
    }

    void dashRedirect(){
        Intent newActivity = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(newActivity);
    }
    void signUpRedirect(){
        Intent newActivity = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(newActivity);
    }
}
