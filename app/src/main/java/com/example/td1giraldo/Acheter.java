package com.example.td1giraldo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class Acheter extends AppCompatActivity {

    TextView textInitial;
    JSONObject json = new JSONObject();
    JSONArray array = new JSONArray();

    public Acheter() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acheter);


        textInitial = (TextView) findViewById(R.id.itemAchat);
        textInitial.setText(getIntent().getStringExtra("itemAchete"));


    }


}
