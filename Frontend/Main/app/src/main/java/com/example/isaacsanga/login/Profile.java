package com.example.isaacsanga.login;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class Profile extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final int IMAGE_RESULT = 1;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.profileImg);
        textView = findViewById(R.id.getName);

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
            Toast.makeText(this, "YAY", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_RESULT && resultCode==RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
        }
    }
}
