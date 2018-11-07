package com.example.sam.moviemap;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button openMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openMap = (Button) findViewById(R.id.openMap);

        openMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
    }

    private void openMap(){
        // Create a Uri from an intent string. Use the result to create an Intent.
        //Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        Uri gmmIntentUri = Uri.parse("geo:0,0?z=10&q=movie+theaters");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
        if(mapIntent.resolveActivity(getPackageManager()) != null)
            startActivity(mapIntent);
    }
}
