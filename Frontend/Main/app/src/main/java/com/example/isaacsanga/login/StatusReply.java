package com.example.isaacsanga.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class StatusReply extends AppCompatActivity {
    private String URL = "http://10.30.186.82:8080/reviews/postReply";
    int movieScore = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_reply);
        Button reply = findViewById(R.id.post);
        final TextView textView = findViewById(R.id.comment);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("user_who_posted", getIntent().getStringExtra("me"));
                    jsonObject.put("review", textView.getText());
                    jsonObject.put("parentID", getIntent().getStringExtra("id"));
                    jsonObject.put("score", movieScore);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }
        });
    }


}
