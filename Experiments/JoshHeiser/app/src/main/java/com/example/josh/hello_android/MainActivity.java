package com.example.josh.hello_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.buttonPlay);
        b2 = (Button) findViewById(R.id.buttonAbout);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This app was created in 2018 by Josh Heiser", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Magic 8 Ball was Created by Mattel Corporation, all Rights reserved", Toast.LENGTH_LONG).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Magic8BallAnswer.class);
                startActivity(i);

            }
        });

    }
}
