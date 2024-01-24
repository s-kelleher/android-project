package com.is6144.a116366313_ca2.Features;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.is6144.a116366313_ca2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


// Slide Animation Between Activites - Android Studio Tutorial
//Youtube Video:https://www.youtube.com/watch?v=0s6x3Sn4eYo


// Bottom Navigation Bar with Activities - Android Advanced Tutorial #6
// Youtube Video: https://www.youtube.com/watch?v=xyGrdOqseuw

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_account);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //highlights nav bar item that is active
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        //bottom navigation bar

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent1 = new Intent(AccountActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.nav_cart:
                        Intent intent2 = new Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_account:

                        break;
                }
                return false;
            }
            //animations used to smoothly swipe between activites.
            public void finish() {
                AccountActivity.super.finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

    }
}
