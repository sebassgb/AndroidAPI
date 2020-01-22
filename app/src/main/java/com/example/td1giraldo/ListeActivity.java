package com.example.td1giraldo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {

    TextView showLoginActivity;
    ListView listeCourses;
    ArrayList<String> listItems=new ArrayList<String>();
    SharedPreferences pref = getApplicationContext().getSharedPreferences("loginName", 0);

    SharedPreferences.Editor editor = pref.edit();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_liste);

        listeCourses = (ListView) findViewById(R.id.listeCourses);
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked);


        showLoginActivity = (TextView)findViewById(R.id.loginFromActivity);
        showLoginActivity.setText(getIntent().getStringExtra("loginText"));
    String username = showLoginActivity.getText().toString();
        Log.i("JBL", username);
        new AsyncGetHTTP().execute(username);

        listeCourses.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elementAchat = ((TextView) view.findViewById(view.getId())).getText().toString();
                Intent myIntent = new Intent(getApplicationContext(),    Acheter.class);
                myIntent.putExtra("itemAchete", elementAchat);
                startActivity(myIntent);
            }

        });

    }

    public class AsyncGetHTTP extends AsyncTask<String, Integer, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params){
            StringBuffer readTextBuf = new StringBuffer();
            JSONObject json = null;
            JSONArray arrayCourses = null;
            BufferedReader bufReader = null;
            InputStreamReader isReader = null;
            InputStream inputStreamReader = null;



            try {
                //Create a URL object holding our url

                URL myUrl = new URL("http://10.0.2.2:8003/courses");

                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();         //Set methods and timeouts
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                //Connect to our url
                connection.connect();

                int status = connection.getResponseCode();
                Log.i("JBL", String.valueOf(status));
                if(status==200){
                    inputStreamReader = connection.getInputStream();
                    isReader = new InputStreamReader(inputStreamReader);
                    bufReader = new BufferedReader(isReader);
                    String line = bufReader.readLine();

                    while(line!= null){
                        readTextBuf.append(line);
                        line = bufReader.readLine();
                    }
                }
                json = new JSONObject(readTextBuf.toString());
                Log.i("JBL", String.valueOf(json));
                arrayCourses = json.getJSONArray(params[0]);

                connection.disconnect();
            }


            catch(IOException | JSONException e){
                e.printStackTrace();
                arrayCourses = null;
            }
            Log.i("JBL", String.valueOf(arrayCourses));
            return arrayCourses;
        }

        @Override
        protected void onPostExecute(JSONArray arrayCourses){
            super.onPostExecute(arrayCourses);


            if(arrayCourses!=null){

                    for (int i=0;i<arrayCourses.length();i++){
                        try {
                            adapter.add(arrayCourses.getString(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                listeCourses.setAdapter(adapter);
                }
        }
    }


}
