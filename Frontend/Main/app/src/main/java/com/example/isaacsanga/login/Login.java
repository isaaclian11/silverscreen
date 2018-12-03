package com.example.isaacsanga.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    final String url = "http://10.26.15.118:8080/users111";

    EditText getEmail, getPassword;
    Button login, registerBtn, findBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getEmail = (EditText) findViewById(R.id.getEmail);
        getPassword = (EditText) findViewById(R.id.getPassword);
        login = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        findBtn = (Button) findViewById(R.id.findBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
        findBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
    }

    //It converts the username and password into Json format and sends it to the server
    private void login() throws JSONException {
        final String email= getEmail.getText().toString();
        String password = getPassword.getText().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", email);
        jsonObject.put("password", password);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.get("result").equals("success")) {
                        //response returns the first and last name of the user
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(getApplicationContext(), ActivityFeed.class);
                        startActivity(loginIntent);
                        CurrentUserInfo currentUserInfo = (CurrentUserInfo) getApplicationContext();
                        currentUserInfo.setUsername(email);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error: Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void openMap(){
        // Create a Uri from an intent string. Use the result to create an Intent.
        //Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=movie+theaters");

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        if(mapIntent.resolveActivity(getPackageManager()) != null)
            startActivity(mapIntent);
    }


}


