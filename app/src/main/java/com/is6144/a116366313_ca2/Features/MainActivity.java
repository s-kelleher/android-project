package com.is6144.a116366313_ca2.Features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.is6144.a116366313_ca2.Adapter.MyAdapter;
import com.is6144.a116366313_ca2.Database.MyDatabaseHelper;
import com.is6144.a116366313_ca2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//Feature included that were not covered in class

//1. Bottom Navigation Bar on HomeActivity, MainActivity and AccountActivity pages
// Bottom Navigation Bar with Activities - Android Advanced Tutorial #6
// Youtube Video: https://www.youtube.com/watch?v=xyGrdOqseuw

//2. Application on-boarding Slider when Application first starts
//Slider Made Using: https://www.javatpoint.com/android-introduction-slider-example

//3. CardView on HomeActivity
//CardView UI Design Android Studio | Using Grid Layout Youtube Video: https://www.youtube.com/watch?v=YKssd_9x8Eg

//4. Animations for Switching Windows
// Video used for activity switching animations https://www.youtube.com/watch?v=0s6x3Sn4eYo
// bottom nav bar https://www.youtube.com/watch?v=xyGrdOqseuw

//Database and Recycler view element based on the below link
// Following link https://www.youtube.com/watch?v=hJPk50p7xwA&list=PLVg409CBpRGcZFIwxtBLufenIZwP1B-op&index=1



public class MainActivity extends AppCompatActivity {

    RecyclerView rvCart;
    FloatingActionButton fabAdd;
    ImageView empty_imageview;
    TextView tvEmpty;

    MyDatabaseHelper myDB;
    ArrayList<String> cart_id, cart_size, cart_base, cart_sauce, cart_cheese, cart_meat;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_main);

        rvCart = findViewById(R.id.rvCart);
        fabAdd = findViewById(R.id.fabAdd);
        empty_imageview = findViewById(R.id.empty_imageview);
        tvEmpty = findViewById(R.id.tvEmpty);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        cart_id = new ArrayList<>();
        cart_size = new ArrayList<>();
        cart_base = new ArrayList<>();
        cart_sauce = new ArrayList<>();
        cart_cheese = new ArrayList<>();
        cart_meat = new ArrayList<>();

        storeDataInArrays();

        myAdapter = new MyAdapter(MainActivity.this, this, cart_id, cart_size, cart_base, cart_sauce, cart_cheese, cart_meat);
        rvCart.setAdapter(myAdapter);
        rvCart.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //Bottom navigation bar

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //highlights nav bar item that is active
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        //animations used to smoothly swipe between activites.
                        Intent intent1 = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        finish();
                        break;
                    case R.id.nav_cart:

                        break;
                    case R.id.nav_account:
                        Intent intent3 = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        break;
                }
                return false;
            }

            public void finish() {
                MainActivity.super.finish();

            }
        });

    }

    //refreshes activity when updating fields
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            recreate();
        }
    }

    public void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                cart_id.add(cursor.getString(0));
                cart_size.add(cursor.getString(1));
                cart_base.add(cursor.getString(2));
                cart_sauce.add(cursor.getString(3));
                cart_cheese.add(cursor.getString(4));
                cart_meat.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    //dialog box to double check if user wants to delete all records from the database
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all the Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();

                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
