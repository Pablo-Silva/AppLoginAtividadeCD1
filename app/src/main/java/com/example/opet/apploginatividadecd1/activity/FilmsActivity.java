package com.example.opet.apploginatividadecd1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.opet.apploginatividadecd1.R;
import com.example.opet.apploginatividadecd1.model.Films;
import com.example.opet.apploginatividadecd1.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FilmsActivity extends AppCompatActivity {

    private TextView textJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        textJSON = findViewById(R.id.textJSON);
    }

    public void onFilmes(View view) {
        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.URL_JSON;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Films films = gson.fromJson(response,Films.class);
                        textJSON.setText(films.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textJSON.setText("Failed to load movies!");
                    }
                });

        queue.add(stringRequest);
    }

    public void backHome(View view){
        homeRedirect();
    }

    void homeRedirect(){
        Intent newActivity = new Intent(FilmsActivity.this,HomeActivity.class);
        startActivity(newActivity);
    }

}
