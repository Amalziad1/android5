package com.example.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // onCreate method is called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your tables here using SQL commands
        String createTableQuery = "CREATE TABLE Customer(ID LONG PRIMARY KEY, NAME TEXT, PHONE TEXT, GENDER TEXT);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCustomer(Customer customer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", customer.getCustomerId());
        contentValues.put("NAME", customer.getName());
        contentValues.put("PHONE", customer.getPhone());
        contentValues.put("GENDER", customer.getGender());
        sqLiteDatabase.insert("Customer", null, contentValues);
        sqLiteDatabase.close(); // Close the database to release resources
    }
    public Cursor getAllCustomers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Customer", null);
    }
    public Cursor getCustomersWithNameStartingWithB() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Customer WHERE NAME LIKE 'B%'", null);
    }
}
