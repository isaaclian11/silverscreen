package com.example.isaacsanga.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button registerBtn;
    TextView registerEmail;
    TextView registerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.registerBtn);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        String url = "http://10.36.49.57/register.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(registerEmail.getText().toString().isEmpty() || registerPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Both fields need to be filled", Toast.LENGTH_SHORT).show();
                }
                else if(response.trim().equals("success")){
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Review.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                String getEmail = registerEmail.getText().toString();
                String getPassword = registerPassword.getText().toString();
                param.put("username", getEmail);
                param.put("password", getPassword);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
