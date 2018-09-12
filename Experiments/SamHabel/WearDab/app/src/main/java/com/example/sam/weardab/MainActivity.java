package com.example.sam.weardab;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends WearableActivity {

    MediaPlayer mp;
    MediaPlayer mp2;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.bassboost);
        mp2 = MediaPlayer.create(this, R.raw.lit);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void playSong(View v) throws InterruptedException {
        Thread.sleep(500);
        mp.start();
        Thread.sleep(1500);
        mp.release();
    }

    public void playSong2(View v) throws InterruptedException {
        Thread.sleep(500);
        mp2.start();
    }
}
