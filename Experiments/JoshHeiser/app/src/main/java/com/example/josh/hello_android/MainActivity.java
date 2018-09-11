package com.example.josh.hello_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.buttonPlay);
        b2 = (Button) findViewById(R.id.buttonAbout);

        b2.setOnClickListener(new View.onClickListener(){
            @Override
            public void OnClick(View view){
                Toast.makeText(getApplicationContext(), "This app was created in 2018 by Josh Heiser", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Magic 8 Ball was Created by Mattel Coporation, all Rights reserved", Toast.LENGTH_LONG).show();

            }
        });

    }
}
