package com.example.sam.reviews;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
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
import com.android.volley.Request.Method;
import com.android.volley.VolleyLog;
import android.util.Log;
import com.example.sam.reviews.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReviewWrite extends AppCompatActivity {

    Button submit;
    EditText authorText, titleText, revText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        submit = (Button) findViewById(R.id.submit);
        authorText = (EditText) findViewById(R.id.authorText);
        titleText = (EditText) findViewById(R.id.titleText);
        revText = (EditText) findViewById(R.id.revText);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                submitReview();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void submitReview(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.androidhive.info/volley/string_response.html";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> param = new HashMap<>();
                param.put("user_who_posted", authorText.getText().toString());
                param.put("film_name", titleText.getText().toString());
                param.put("review", revText.getText().toString());
                return param;
            }
        };
        queue.add(stringRequest);
    }
}
