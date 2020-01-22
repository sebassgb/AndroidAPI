package com.example.td1giraldo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button   mButton,quitterButton, coursesButton;
    TextView mEdit, tLogin;
    TimePicker timeCourses;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.androidtest);

        //Buttons
        quitterButton = (Button)findViewById(R.id.buttonQuitter);
        coursesButton = (Button)findViewById(R.id.buttonCourses);
        mButton = (Button)findViewById(R.id.buttonGetText);


        //Texts
        mEdit   = (TextView)findViewById(R.id.textFromButton);
        tLogin = (TextView)findViewById(R.id.editLogin);

        //Widgets TimePicker
        timeCourses = (TimePicker)findViewById(R.id.timePickerCourses);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {

                    public void onClick(View view)
                    {
                        mEdit.setText(tLogin.getText().toString() + " doit faire les courses a " + timeCourses.getCurrentHour()+"h"+ timeCourses.getCurrentMinute());
                    }
                });

        coursesButton.setOnClickListener(new View.OnClickListener()
                {

                    public void onClick(View view)
                    {
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("loginName", 0);// n'a pas besoin d'etre static
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("loginName", tLogin.getText().toString()); // Storing string
                        Intent myIntent = new Intent(view.getContext(), ListeActivity.class);
                        myIntent.putExtra("loginText", tLogin.getText().toString());
                        startActivity(myIntent);
                    }
                });

        quitterButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        finish();
                        System.exit(0);
                    }
                });

    }




}
