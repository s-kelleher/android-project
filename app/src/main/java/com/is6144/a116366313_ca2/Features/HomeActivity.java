package com.is6144.a116366313_ca2.Features;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.is6144.a116366313_ca2.R;

// Bottom Navigation Bar with Activities - Android Advanced Tutorial #6
// Youtube Video: https://www.youtube.com/watch?v=xyGrdOqseuw

// CardView UI Design Android Studio | Using Grid Layout
//Youtube Video: https://www.youtube.com/watch?v=YKssd_9x8Eg

// Slide Animation Between Activites - Android Studio Tutorial
//Youtube Video:https://www.youtube.com/watch?v=0s6x3Sn4eYo


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Bottom Nav Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Active Nav Bar Item Highlighted
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_cart:

                        Intent intent2 = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_account:
                        Intent intent3 = new Intent(HomeActivity.this, AccountActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                }
                return false;
            }

            //animations used to smoothly swipe between activites
            public void finish() {
                HomeActivity.super.finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }
}

