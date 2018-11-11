package com.example.isaacsanga.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private static final int IMAGE_RESULT = 1;
    RecyclerView listView;
    Bitmap bitmap;
    String URL = "http://10.30.186.82:8080/review/friendsReview";
    ImageView findFriends, latestMovies;
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<String>names = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> movieID = new ArrayList<>();
    ArrayList<Integer> movieScore = new ArrayList<>();
    ArrayList<Model> arrayList = new ArrayList<>();
    ArrayList<String> movieTitle = new ArrayList<>();
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        listView = findViewById(R.id.activityFeed);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_who_posted", getIntent().getStringExtra("username"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("response");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject reviews = jsonArray.getJSONObject(i);
                        id.add(reviews.getInt("id"));
                        names.add(reviews.getString("user_who_posted"));
                        descriptions.add(reviews.getString("review"));
                        movieID.add(reviews.getString("posterID"));
                        movieScore.add(reviews.getInt("score"));
                        movieTitle.add(reviews.getString("movieTitle"));
                    }
                    for(int i=0; i<names.size(); i++){
                        Model model = new Model(id.get(i), movieTitle.get(i), names.get(i), descriptions.get(i), movieID.get(i), getIntent().getStringExtra("username"), movieScore.get(i));
                        arrayList.add(model);
                    }
                    listViewAdapter = new ListViewAdapter(getApplicationContext(), arrayList);
                    listView.setAdapter(listViewAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);
        listView.setLayoutManager(new LinearLayoutManager(this));



        findFriends = findViewById(R.id.findFriends);
        latestMovies = findViewById(R.id.latestMovies);

        latestMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LatestMovies.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
    }



}
