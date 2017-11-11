package com.example.zhai.auduiobar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AudioBarView audioBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioBarView = (AudioBarView) findViewById(R.id.audio_view);
    }

    public void change(View view) {
        float random = (float) Math.random();
        audioBarView.changeAudioBarHeight(random);
    }
}
