package com.example.td1giraldo;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.View;

public class Preferences extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("loginName", 0);// n'a pas besoin d'etre static
        SharedPreferences.Editor editor = pref.edit();
    }

}
