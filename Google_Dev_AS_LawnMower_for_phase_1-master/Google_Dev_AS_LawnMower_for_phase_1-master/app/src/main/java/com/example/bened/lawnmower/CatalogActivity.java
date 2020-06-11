package com.example.bened.lawnmower;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.bened.lawnmower.data.LawnMowerContract;
import com.example.bened.lawnmower.data.MowerDbHelper;

import static java.lang.String.valueOf;

public class CatalogActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private MowerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        //Set the toolbar as the app bar for the activity
        Toolbar topToolbar = findViewById(R.id.header_data_toolbar);
        setSupportActionBar(topToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.catalog_activity_title);
        }

        // Set the FAB of the activity and its click listener
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openEditor = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(openEditor);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new MowerDbHelper(this);
        displayDatabaseInfo();
    }


    // Temporary helper method to display information in the onscreen TextView about the state of
    // the mowers table.
    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query:
        //"SELECT  mower_name, price, supplier_name, supplier_phone  FROM mowers"
        // to get a Cursor that contains those specific rows from the mowers table.
        // Cursor cursor = db.rawQuery
        // ("SELECT mower_name, price, supplier_name, supplier_phone  FROM "
        // + LawnmowerContract.LawnmowerEntry.TABLE_NAME, null);

        String[] projection = {
                LawnMowerContract.LawnMowerEntry._ID,
                LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_NAME,
                LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_PRICE,
                LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_NAME,
                LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_PHONE
        };

        Cursor c = db.query(
                LawnMowerContract.LawnMowerEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        //Define the view where the projection will occur
        TextView textViewData = findViewById(R.id.text_view_data);

        try {
            //iterate through the rows of the cursor and display
            // the information from each column in this order.
            Resources res = getResources();
            String dataDisplay1 = res.getString(R.string.data_display_1);
            String dataDisplay2 = res.getString(R.string.data_display_2);
            int numberOfMowers = c.getCount();
            String number = valueOf(numberOfMowers);
            String carriageReturn = "\n";
            String space = " ";

            textViewData.setText((dataDisplay1 + space + number + space + dataDisplay2 + carriageReturn + carriageReturn + carriageReturn).toLowerCase());

            textViewData.append(
                    LawnMowerContract.LawnMowerEntry._ID + " - " +
                            LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_NAME + " , " +
                            LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_PRICE + " , " +
                            LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_NAME + " , " +
                            LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_PHONE + carriageReturn
            );

            // Figure out the index of each column
            int idColumnIndex = c.getColumnIndex(LawnMowerContract.LawnMowerEntry._ID);
            int mowerNameColumnIndex = c.getColumnIndex(LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_NAME);
            int priceColumnIndex = c.getColumnIndex(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_PRICE);
            int supplierNameColumnIndex = c.getColumnIndex(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = c.getColumnIndex(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor
            while (c.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = c.getInt(idColumnIndex);
                String currentMowerName = c.getString(mowerNameColumnIndex);
                String currentPrice = c.getString(priceColumnIndex);
                String currentSupplierName = c.getString(supplierNameColumnIndex);
                String currentSupplierPhone = c.getString(supplierPhoneColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                textViewData.append((
                        "\n" + currentID + " - " +
                                currentMowerName + " - " +
                                currentPrice + " - " +
                                currentSupplierName + " - " +
                                currentSupplierPhone
                ));
            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            c.close();
        }

    }

    // Inflate the menu options from the res/menu/menu_catalog.xml file.
    // This adds menu items to the app bar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    //Refresh the list with the new mower in the database after the user saved it
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
