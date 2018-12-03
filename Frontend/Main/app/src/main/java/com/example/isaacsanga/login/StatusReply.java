package com.example.isaacsanga.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StatusReply extends AppCompatActivity {
    String URL = "http://10.26.48.202:8080/review/newReview";
    String replyURL = "http://10.26.48.202:8080/review/replies";
    ReplyAdapter replyAdapter;
    ArrayList<ReplyModel> replyModels = new ArrayList<>();
    RecyclerView recyclerView;
    int movieScore = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_reply);
        final Button reply = findViewById(R.id.post);
        final EditText textView = findViewById(R.id.comment);
        recyclerView = findViewById(R.id.replyList);
        final JSONObject jsonObject = new JSONObject();
        try {
            if(Integer.parseInt(getIntent().getStringExtra("parentID"))==0)
                jsonObject.put("id", getIntent().getStringExtra("id"));
            else
                jsonObject.put("id", getIntent().getStringExtra("parentID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, replyURL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("response");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject current = jsonArray.getJSONObject(i);
                        ReplyModel replyModel = new ReplyModel(current.getString("review"), current.getString("user_who_posted"));
                        replyModels.add(replyModel);
                    }
                    replyAdapter = new ReplyAdapter(getApplicationContext(), replyModels);
                    recyclerView.setAdapter(replyAdapter);
                    recyclerView.scrollToPosition(replyModels.size()-1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("user_who_posted", ((CurrentUserInfo) getApplication()).getUsername());
                    jsonObject.put("review", textView.getText().toString());
                    if(Integer.parseInt(getIntent().getStringExtra("parentID"))==0)
                        jsonObject.put("parentID", getIntent().getStringExtra("id"));
                    else
                        jsonObject.put("parentID", getIntent().getStringExtra("parentID"));                    jsonObject.put("movie_title", getIntent().getStringExtra("movie_title"));
                    jsonObject.put("posterID", getIntent().getStringExtra("posterID"));

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
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
                replyModels.add(new ReplyModel(textView.getText().toString(), ((CurrentUserInfo) getApplication()).getUsername()));
                replyAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(replyModels.size()-1);
                textView.getText().clear();
            }
        });
    }


}
