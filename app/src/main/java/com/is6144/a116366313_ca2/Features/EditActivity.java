package com.is6144.a116366313_ca2.Features;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.is6144.a116366313_ca2.Database.MyDatabaseHelper;
import com.is6144.a116366313_ca2.R;

// Bottom Navigation Bar with Activities - Android Advanced Tutorial #6
// Youtube Video: https://www.youtube.com/watch?v=xyGrdOqseuw


public class EditActivity extends AppCompatActivity {

    EditText etSize2, etBase2, etSauce2, etCheese2, etMeat2;
    Button btnEdit, btnDelete;

    String id, size, base, sauce, cheese, meat;

    private static final String TAG = "***TESTING***";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Stop keyboard opening at start of activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_edit);

        etSize2 = findViewById(R.id.etSize2);
        etBase2 = findViewById(R.id.etBase2);
        etSauce2 = findViewById(R.id.etSauce2);
        etCheese2 = findViewById(R.id.etCheese2);
        etMeat2 = findViewById(R.id.etMeat2);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        //Calling method
        getAndSetIntentData();

        //Set Action Bar Title
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(size);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(EditActivity.this);
                size = etSize2.getText().toString().trim();
                base = etBase2.getText().toString().trim();
                sauce = etSauce2.getText().toString().trim();
                cheese = etCheese2.getText().toString().trim();
                meat = etMeat2.getText().toString().trim();
                myDB.updateData(id, size, base, sauce, cheese, meat);

                Log.d(TAG, "Edit Button clciked");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(EditActivity.this);
                myDB.deleteRow(id);

                confirmDialog();

                //Log Delete button clicked
                Log.d(TAG, "Delete Button");
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("size") &&
                getIntent().hasExtra("base") && getIntent().hasExtra("sauce")&& getIntent().hasExtra("cheese")&& getIntent().hasExtra("meat")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            size = getIntent().getStringExtra("size");
            base = getIntent().getStringExtra("base");
            sauce = getIntent().getStringExtra("sauce");
            cheese = getIntent().getStringExtra("cheese");
            meat = getIntent().getStringExtra("meat");

            //Setting Intent Data
            etSize2.setText(size);
            etBase2.setText(base);
            etSauce2.setText(sauce);
            etCheese2.setText(cheese);
            etMeat2.setText(meat);


        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    //Confirm Dialogue method confirming user wants to delete row
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + size + " ?");
        builder.setMessage("Are you sure you want to delete " + size + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(EditActivity.this);
                myDB.deleteRow(id);
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
