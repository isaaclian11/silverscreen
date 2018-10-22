package com.example.isaacsanga.login;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final String url = "http://proj309-sb-07.misc.iastate.edu:8080/users111";

    EditText getEmail, getPassword;
    Button login, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getEmail = (EditText) findViewById(R.id.getEmail);
        getPassword = (EditText) findViewById(R.id.getPassword);
        login = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.registerBtn);

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
        
    }

    private void login() throws JSONException {
        String email= getEmail.getText().toString();
        String password = getPassword.getText().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", email);
        jsonObject.put("password", password);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.get("respons").equals("success")) {
                        String firstname = response.getString("firstname");
                        String lastname = response.getString("lastname");
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(getApplicationContext(), Profile.class);
                        loginIntent.putExtra("firstname", firstname);
                        loginIntent.putExtra("lastname", lastname);
                        startActivity(loginIntent);
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


}


