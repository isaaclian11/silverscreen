package com.example.isaacsanga.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Profile extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final int IMAGE_RESULT = 1;
    ImageView imageView;
    TextView textView;
    ListView listView;
    Bitmap bitmap;
    String URL = "http://10.36.49.189:8080/review/friendsReview";
    Button findFriends, latestMovies;
    ArrayList<String>names = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> movieID = new ArrayList<>();
    ArrayList<Integer> movieScore = new ArrayList<>();
    ArrayList<Model> arrayList = new ArrayList<>();
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.userProfile);
        textView = findViewById(R.id.getName);
        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        textView.setText(firstname + " " +lastname);
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
                        names.add(reviews.getString("user_who_posted"));
                        descriptions.add(reviews.getString("review"));
                        movieID.add(reviews.getString("posterID"));
                        movieScore.add(reviews.getInt("score"));
                    }
                    for(int i=0; i<names.size(); i++){
                        Model model = new Model(names.get(i), descriptions.get(i), movieID.get(i), getIntent().getStringExtra("username"), movieScore.get(i));
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Profile.this, imageView);
                popupMenu.setOnMenuItemClickListener(Profile.this);
                popupMenu.inflate(R.menu.profile_picture);
                popupMenu.show();
            }
        });

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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId()==R.id.profile_menu){
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, IMAGE_RESULT);
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_RESULT && resultCode==RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                uploadImg();
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImg() throws JSONException {
        String image = getStringImg(bitmap);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("image", image);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.get("result").equals("success"))
                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public String getStringImg(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        String encode = Base64.encodeToString(imageByte, Base64.DEFAULT);
        return encode;
    }

}
