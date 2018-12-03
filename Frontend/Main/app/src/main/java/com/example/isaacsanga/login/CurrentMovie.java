package com.example.isaacsanga.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class CurrentMovie extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    ImageView poster;
    EditText review;
    TextView score, summary, title;
    Button post;
    int movieScore = -1;
    private String API = "?api_key=cf48d0b2aede68f37177a9c799b06a45&language=en-US";
    String movieInfoURL = "https://api.themoviedb.org/3/movie/";
    String postReviewURL = "http://10.29.181.149:8080/review/newReview";
    String movieTitle, posterURL;
    CurrentUserInfo currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_movie);

        poster = findViewById(R.id.currentMoviePoster);
        review = findViewById(R.id.currentMovieReview);
        score = findViewById(R.id.currentMovieScore);
        summary = findViewById(R.id.currentMovieSummary);
        title = findViewById(R.id.currentMovieTitle);
        post = findViewById(R.id.currentMoviePost);

        currentUser = (CurrentUserInfo) getApplicationContext();
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(CurrentMovie.this, score);
                        popupMenu.setOnMenuItemClickListener(CurrentMovie.this);
                        popupMenu.inflate(R.menu.score);
                        popupMenu.show();
                    }
                });
            }
        });

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, movieInfoURL + getIntent().getStringExtra("movieID") + API, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    posterURL = response.getString("poster_path");
                    Picasso.get().load("https://image.tmdb.org/t/p/w500"+posterURL).into(poster);
                    movieTitle = response.getString("title");
                    title.setText(movieTitle);
                    String summaryOfMovie = response.getString("overview");
                    summary.setText(summaryOfMovie);


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

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(review.getText().toString().isEmpty() || movieScore==-1){
                    Toast.makeText(getApplicationContext(), "Empty field", Toast.LENGTH_SHORT).show();
                }
                else{
                    JSONObject reviewInfo = new JSONObject();
                    try {
                        reviewInfo.put("user_who_posted",  currentUser.getUsername());
                        reviewInfo.put("movieID", getIntent().getStringExtra("movieID"));
                        reviewInfo.put("review", review.getText().toString());
                        reviewInfo.put("score", movieScore);
                        reviewInfo.put("posterID", posterURL);
                        reviewInfo.put("movie_title", movieTitle);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, postReviewURL, reviewInfo, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getString("result").equals("success")){
                                    review.getText().clear();
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                    requestQueue1.add(jsonObjectRequest1);
                }
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
