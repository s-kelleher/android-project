package com.is6144.a116366313_ca2.Slider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.is6144.a116366313_ca2.R;

//Slider Made Using: https://github.com/IAmDarush/sample-android-intro-slider/blob/master/app/src/main/java/com/example/myintroslider/PrefManager.java
public class SliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We normally won't show the welcome slider again in real app
                // but this is for testing
                PrefManager prefManager = new PrefManager(getApplicationContext());

                // make first time launch TRUE
                prefManager.setFirstTimeLaunch(false);

                startActivity(new Intent(SliderActivity.this, WelcomeActivity.class));
                finish();
            }
        });
    }
}
