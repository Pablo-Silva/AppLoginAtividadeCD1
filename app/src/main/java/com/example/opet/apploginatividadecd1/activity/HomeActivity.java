package com.example.opet.apploginatividadecd1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.opet.apploginatividadecd1.R;
import com.example.opet.apploginatividadecd1.model.Films;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private TextView textHome;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textHome = findViewById(R.id.textHome);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
        String menssage = "Welcome! Authenticated as:" + mAuth.getCurrentUser().getEmail();
        textHome.setText(menssage);
    }

    public void logout(View view) {
        mAuth.signOut();
        mainRedirect();
    }

    void mainRedirect(){
        Intent newActivity = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(newActivity);
    }

    public void onFilms(View view){
        filmsRedirect();
    }

    void filmsRedirect(){
        Intent newActivity = new Intent(HomeActivity.this, FilmsActivity.class);
        startActivity(newActivity);
    }
}
