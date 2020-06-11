package com.example.bened.lawnmower;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bened.lawnmower.data.LawnMowerContract;
import com.example.bened.lawnmower.data.MowerDbHelper;

import static android.widget.Spinner.OnItemSelectedListener;

public class EditorActivity extends AppCompatActivity {

    private Spinner mainCategorySpinner, subCategorySpinner;

    private EditText mowerNameInput, seriesNameInput, priceInput, quantityInput,
            supplierNameInput, supplierCountryInput, supplierPhoneInput;

    private String mainCategorySpinnerItem;
    private String subCategorySpinnerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        //Set the toolbar as the app bar for the activity
        Toolbar topToolbar = findViewById(R.id.header_data_toolbar);
        setSupportActionBar(topToolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle(R.string.editor_activity_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Find all relevant views needed to read user input from
        mainCategorySpinner = findViewById(R.id.main_category_spinner);
        subCategorySpinner = findViewById(R.id.sub_category_spinner);
        mowerNameInput = findViewById(R.id.mower_name_input);
        seriesNameInput = findViewById(R.id.series_name_input);
        priceInput = findViewById(R.id.price_input);
        quantityInput = findViewById(R.id.quantity_input);
        supplierNameInput = findViewById(R.id.supplier_name_input);
        supplierCountryInput = findViewById(R.id.supplier_country_input);
        supplierPhoneInput = findViewById(R.id.supplier_phone_input);

        setMainCategorySpinner();
        setSubCategorySpinner();
    }

    //Setup the dropdown spinners
    private void setMainCategorySpinner() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> mainCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.main_category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        mainCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mainCategorySpinner.setAdapter(mainCategoryAdapter);
        //define the selection event handler for the spinner
        mainCategorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> mainCategoryAdapter, View view, int position, long id) {
                mainCategorySpinnerItem = mainCategoryAdapter.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSubCategorySpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> subCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.sub_category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        subCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        subCategorySpinner.setAdapter(subCategoryAdapter);
        //define the selection event handler for the spinner
        subCategorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> subCategoryAdapter, View view, int position, long id) {
                subCategorySpinnerItem = subCategoryAdapter.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }



    /*final Resources resources = getResources();
    final TypedArray mainCategoryItem = resources.obtainTypedArray(R.array.main_category_array);
    final TypedArray subCategoryItem = resources.obtainTypedArray(R.array.sub_category_array);*/

    //Get user input from editor
    private void insertMower() {
        String mainCategorySpinnerStr = mainCategorySpinnerItem;
        String subCategorySpinnerStr = subCategorySpinnerItem;
        String mowerNameInputStr = mowerNameInput.getText().toString().trim();
        String seriesNameInputStr = seriesNameInput.getText().toString().trim();
        String priceInputStr = priceInput.getText().toString().trim();
        String quantityInputStr = quantityInput.getText().toString().trim();
        int quantity = Integer.parseInt(quantityInputStr);
        String supplierNameInputStr = supplierNameInput.getText().toString().trim();
        String supplierCountryInputStr = supplierCountryInput.getText().toString().trim();
        String supplierPhoneInputStr = supplierPhoneInput.getText().toString().trim();

        //Create database helper
        MowerDbHelper mDbHelper = new MowerDbHelper(this);

        //Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        //Create a ContentValues object where column names are the keys,
        //and mowers' attributes extracted from the editor activity are the values
        ContentValues values = new ContentValues();
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_CATEGORY, mainCategorySpinnerStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_SUB_CATEGORY, subCategorySpinnerStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWER_NAME, mowerNameInputStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN__SERIES_NAME, seriesNameInputStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_PRICE, priceInputStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_QUANTITY, quantityInputStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_NAME, supplierNameInputStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_COUNTRY, supplierCountryInputStr);
        values.put(LawnMowerContract.LawnMowerEntry.COLUMN_MOWERS_SUPPLIER_PHONE, supplierPhoneInputStr);

        //insert a new row for mower in the database, returning the ID of that new row.
        long newRowId = db.insert(LawnMowerContract.LawnMowerEntry.TABLE_NAME, null, values);

        //Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            //If the row ID is -1, then there was an error with insertion
            Toast.makeText(this, "error with saving the lawnmower", Toast.LENGTH_SHORT).show();
        } else {

            //Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "the lawnmower id saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }

    // Inflate the menu options from the res/menu/menu_editor.xml file.
    // This adds menu items to the app bar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Do nothing for now
                //Save pet to database
                insertMower();
                //Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}