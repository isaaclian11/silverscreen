package com.example.isaacsanga.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    ImageView findFriends, latestMovies, home, profile, logout;
    TextView postCount, likeCount;
    RecyclerView recyclerView;
    String URL = "http://10.26.50.117:8080/review/myReviews";
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String>names = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> movieID = new ArrayList<>();
    ArrayList<Integer> movieScore = new ArrayList<>();
    ArrayList<Model> arrayList = new ArrayList<>();
    ArrayList<String> movieTitle = new ArrayList<>();
    ArrayList<Integer> parentID = new ArrayList<>();
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.menubar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.ProfileMyPosts);

        findFriends = findViewById(R.id.findFriends);
        latestMovies = findViewById(R.id.latestMovies);
        home = findViewById(R.id.homeFeed);
        profile = findViewById(R.id.getProfile);
        postCount = findViewById(R.id.ProfilePostCount);
        likeCount = findViewById(R.id.ProfileLikeCount);

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_who_posted", ((CurrentUserInfo) getApplication()).getUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("response");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject reviews = jsonArray.getJSONObject(i);
                        id.add(reviews.getInt("id"));
                        names.add(reviews.getString("user_who_posted"));
                        descriptions.add(reviews.getString("review"));
                        movieID.add(reviews.getString("posterID"));
                        movieScore.add(reviews.getInt("score"));
                        movieTitle.add(reviews.getString("movie_title"));
                        parentID.add(reviews.getInt("parentID"));
                    }
                    for(int i=0; i<names.size(); i++){
                        Model model = new Model(id.get(i), movieTitle.get(i), names.get(i), descriptions.get(i), movieID.get(i), parentID.get(i), movieScore.get(i));
                        arrayList.add(model);
                    }
                    listViewAdapter = new ListViewAdapter(getApplicationContext(), arrayList);
                    recyclerView.setAdapter(listViewAdapter);
                    postCount.setText(Integer.toString(arrayList.size())+"\n" + "Posts");
                    likeCount.setText("0" + "\n" + "Favorites");

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Friends.class);
                startActivity(intent);
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
                
            }
        });

        logout = findViewById(R.id.ProfileLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}
