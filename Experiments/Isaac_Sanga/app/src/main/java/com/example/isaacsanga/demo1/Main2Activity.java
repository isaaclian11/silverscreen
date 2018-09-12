package com.example.isaacsanga.demo1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button googlBtn = findViewById(R.id.googleBtn);
        googlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String google = "http://www.google.com";
                Uri url = Uri.parse(google);
                Intent toGoogle = new Intent(Intent.ACTION_VIEW, url);
                if(toGoogle.resolveActivity(getPackageManager())!=null)
                    startActivity(toGoogle);

            }
        });
    }
}
