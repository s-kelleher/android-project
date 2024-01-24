package com.is6144.a116366313_ca2.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.is6144.a116366313_ca2.Database.MyDatabaseHelper;
import com.is6144.a116366313_ca2.R;

//Database and Recycler view element based on the below link
// Following link https://www.youtube.com/watch?v=hJPk50p7xwA&list=PLVg409CBpRGcZFIwxtBLufenIZwP1B-op&index=1

public class AddActivity extends AppCompatActivity {

    EditText etSize, etBase, etSauce, etCheese, etMeat;
    Button btnSave;

    private static final String TAG = "***TESTING***";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this stops the keyboard from opening up on activity open
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_add);

        etSize = findViewById(R.id.etSize);
        etBase = findViewById(R.id.etBase);
        etSauce = findViewById(R.id.etSauce);
        etCheese = findViewById(R.id.etCheese);
        etMeat = findViewById(R.id.etMeat);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                String title = etSize.getText().toString().trim();
                String subtitle = etBase.getText().toString().trim();
                String startDate = etSauce.getText().toString().trim();
                String endDate = etCheese.getText().toString().trim();
                String endTime = etMeat.getText().toString().trim();

                //error handling to ensure all textboxes have been filled
                if (title.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all mandatory details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (subtitle.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all mandatory details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (startDate.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all mandatory details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (endDate.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all mandatory details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (endTime.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all mandatory details", Toast.LENGTH_SHORT).show();
                    return;
                }


                //Log of Save button
                Log.d(TAG, myDB.toString());

                myDB.addCart(etSize.getText().toString(),
                        etBase.getText().toString(),
                        etSauce.getText().toString(),
                        etCheese.getText().toString(),
                        etMeat.getText().toString());

                //clears textboxes when data has successfully been entered into db
                etSize.setText("");
                etBase.setText("");
                etSauce.setText("");
                etCheese.setText("");
                etMeat.setText("");


            }
        });

    }
}