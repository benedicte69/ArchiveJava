package com.example.bened.lawnmower.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MowerDbHelper extends SQLiteOpenHelper {

    //Database version. If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    //Name of the database file
    private static final String DATABASE_NAME = "gardening.db";


    /**
     * Constructs a new instance of {@link MowerDbHelper}.
     *
     * @param context of the app
     */
    public MowerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //This is called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the mowers table
        String SQL_CREATE_MOWERS_TABLE =
                "CREATE TABLE " + LawnMowerContract.LawnMowerEntry.TABLE_NAME + "("
                        + LawnMowerContract.LawnMowerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_CATEGORY + " TEXT NOT NULL, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_SUB_CATEGORY + " TEXT NOT NULL, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_NAME + " TEXT NOT NULL, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN__SERIES_NAME + " TEXT, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_PRICE + " TEXT NOT NULL, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_QUANTITY + " INTEGER NOT NULL DEFAULT '0', "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_NAME + " TEXT, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_COUNTRY + " TEXT, "
                        + LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_PHONE + " TEXT)";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_MOWERS_TABLE);
    }

    //This is called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
