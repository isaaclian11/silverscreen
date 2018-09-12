package com.example.isaacsanga.demo1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    Boolean llama = false;
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
            }

            );
        final ImageView llamaImg = findViewById(R.id.llamaImg);
        llamaImg.setVisibility(View.INVISIBLE);
        final Button llamaBtn = findViewById(R.id.llamaBtn);
        llamaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!llama) {
                    llamaImg.setVisibility(View.VISIBLE);
                    llama = true;
                }
                else{
                    llamaImg.setVisibility(View.INVISIBLE);
                    llama = false;
                }
            }
        });
        llamaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String llamaURL = "https://en.wikipedia.org/wiki/Llama";
                Uri url = Uri.parse(llamaURL);
                Intent wiki = new Intent(Intent.ACTION_VIEW, url);
                if(wiki.resolveActivity(getPackageManager())!=null){
                    startActivity(wiki);
                }
            }
        });
    }

}
