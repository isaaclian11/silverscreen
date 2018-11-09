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

public class StatusReply extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private String URL = "http://10.36.49.189:8080/reviews/postReply";
    int movieScore = -1;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_reply);
        Button reply = findViewById(R.id.post);
        score = findViewById(R.id.score);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(StatusReply.this, score);
                popupMenu.setOnMenuItemClickListener(StatusReply.this);
                popupMenu.inflate(R.menu.score);
                popupMenu.show();
            }
        });
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.one:
                movieScore= 1;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.two:
                movieScore = 2;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.three:
                movieScore = 3;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.four:
                movieScore = 4;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.five:
                movieScore = 5;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.six:
                movieScore = 6;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.seven:
                movieScore = 7;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.eight:
                movieScore = 8;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.nine:
                movieScore = 9;
                score.setText(Integer.toString(movieScore));
                break;
            case R.id.ten:
                movieScore = 10;
                score.setText(Integer.toString(movieScore));
                break;
        }
        return true;
    }
}
