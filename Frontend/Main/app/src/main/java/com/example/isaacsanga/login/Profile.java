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
import com.android.volley.toolbox.Volley;

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
    String URL = "http://proj309-sb-07.misc.iastate.edu:8080";
    String[] names;
    String[] descriptions;
    int[] pictures;
    ArrayList<Model> arrayList = new ArrayList<>();
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.name);
        textView = findViewById(R.id.getName);
        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        textView.setText(firstname + " " +lastname);
        listView = findViewById(R.id.activityFeed);

        names = new String[]{"Isaac Lal", "Isaac Sanga", "Isaac Lian", "Isaac Din", "Isaac S"};
        descriptions = new String[]{"Bohemian Rhapsody, the long-awaited musical biopic about British rock band Queen and particularly their lead singer Freddie Mercury, traveled a rocky road during its journey to the big screen. The movie went through multiple changes in personnel (lead actor in particular) before Rami Malek was cast as Mercury and Bryan Singer signed on to direct. Bohemian Rhapsody's woes didn't end there either, as Singer was fired in the midst of production for his unexplained absence from the film's set, after clashing with the cast/crew. Unfortunately, the final movie result doesn't really justify all the fuss it took to get made, either. Despite a strong performance by Malek, Bohemian Rhapsody plays out as an excessively sanitized version of Queen's story, rather than a labor of love.\n" +
                "\n" +
                "The film picks up in London circa 1970, when Freddie (then still going by his birth name, Farrokh Bulsara) is a college-aged young man who works as a baggage handler at Heathrow Airport, but intends to make his name as a musician. One night, after watching local up and comer band Smile perform, Freddie convinces their guitarist Brian May (Gwilym Lee) and drummer Roger Taylor (Ben Hardy) to make him their new leader singer, after giving them a taste of his incredible vocal range. The three later add bass guitarist John Deacon (Joseph Mazzello) to their ranks and dub their new band Queen (a name picked out by Freddie).", "Fantastic film", "Worst film", "Really enjoyed it", "Want to see it again!"};
        pictures = new int[]{R.drawable.user1, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.user5};

        for(int i=0; i<names.length; i++){
            Model model = new Model(names[i], descriptions[i], pictures[i]);
            arrayList.add(model);
        }

        listViewAdapter = new ListViewAdapter(this, arrayList);

        listView.setAdapter(listViewAdapter);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Profile.this, imageView);
                popupMenu.setOnMenuItemClickListener(Profile.this);
                popupMenu.inflate(R.menu.profile_picture);
                popupMenu.show();
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
