package com.example.isaacsanga.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Friends extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FriendsModel> friendsModels = new ArrayList<>();
    String URL = "http://10.30.186.53:8080/myFriends";
    ImageView findFriends, latestMovies, home, profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Toolbar toolbar = findViewById(R.id.menubar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.friendsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String username = ((CurrentUserInfo) this.getApplication()).getUsername();

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("response");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject result = jsonArray.getJSONObject(i);
                        friendsModels.add(new FriendsModel(result.getString("username")));
                    }
                    FriendsListAdapter friendsListAdapter = new FriendsListAdapter(getApplicationContext(), friendsModels);
                    recyclerView.setAdapter(friendsListAdapter);
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

        findFriends = findViewById(R.id.findFriends);
        latestMovies = findViewById(R.id.latestMovies);
        home = findViewById(R.id.homeFeed);
        profile = findViewById(R.id.getProfile);

        findFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        latestMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LatestMovies.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityFeed.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

    }
}
