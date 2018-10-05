package com.example.sam.reviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] MOVIES = {"Jurassic World", "The Godfather", "A", "B", "C", "D", "E"};
    String[] DESCRIPTIONS = {"Test", "Test", "Test", "Test", "Test", "Test", "Test"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView)findViewById(R.id.listView);
    }


}
