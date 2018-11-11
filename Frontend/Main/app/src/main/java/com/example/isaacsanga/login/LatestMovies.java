package com.example.isaacsanga.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;

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

public class LatestMovies extends AppCompatActivity {

    private final String APIKey = "cf48d0b2aede68f37177a9c799b06a45";
    String getLatest = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    LatestMovieListAdapter latestMovieListAdapter;
    RecyclerView listView;
    ArrayList<MovieModel> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_movies);
        Toolbar toolbar = findViewById(R.id.menubar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.movieList);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getLatest + APIKey, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int movieID = jsonObject.getInt("id");
                        String movieTitle = jsonObject.getString("title");
                        String posterUrl = jsonObject.getString("poster_path");
                        movies.add(new MovieModel(movieID, movieTitle, posterUrl, getIntent().getStringExtra("username")));
                    }
                    latestMovieListAdapter = new LatestMovieListAdapter(getApplicationContext(), movies);
                    listView.setAdapter(latestMovieListAdapter);
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
        listView.setLayoutManager(new GridLayoutManager(this, 3));

   }
}
