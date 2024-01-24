package com.is6144.a116366313_ca2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

//Database and Recycler view element
// Youtube video https://www.youtube.com/watch?v=hJPk50p7xwA&list=PLVg409CBpRGcZFIwxtBLufenIZwP1B-op&index=2

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //declare database name, table and columns
    private Context context;
    private static final String DATABASE_NAME = "Cart.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_cart";

    //Access Modifiers
    private static final String COL_ID = "id";
    private static final String COL_SIZE = "size";
    private static final String COL_BASE = "base";
    private static final String COL_SAUCE = "sauce";
    private static final String COL_CHEESE = "cheese";
    private static final String COL_MEAT = "meat";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Create Table to insert data
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_SIZE + " TEXT, " +
                        COL_BASE + " TEXT, " +
                        COL_SAUCE + " TEXT, " +
                        COL_CHEESE + " TEXT, " +
                        COL_MEAT + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //Database Queries
    // Insert Data in Database Table
    public void addCart(String size, String base, String sauce, String cheese, String meat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_SIZE, size);
        cv.put(COL_BASE, base);
        cv.put(COL_SAUCE, sauce);
        cv.put(COL_CHEESE, cheese);
        cv.put(COL_MEAT, meat);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to insert", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully inserted", Toast.LENGTH_SHORT).show();
        }
    }

    //Read Table data
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //Update Table Data
    public void updateData(String row_id, String size, String base, String sauce, String cheese, String meat) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SIZE, size);
        cv.put(COL_BASE, base);
        cv.put(COL_SAUCE, sauce);
        cv.put(COL_CHEESE, cheese);
        cv.put(COL_MEAT, meat);

        long result = db.update(TABLE_NAME, cv, " id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    //Delete table data
    public void deleteRow(String row_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, " id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    //delete all records from table
    public void deleteAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);

    }

}
