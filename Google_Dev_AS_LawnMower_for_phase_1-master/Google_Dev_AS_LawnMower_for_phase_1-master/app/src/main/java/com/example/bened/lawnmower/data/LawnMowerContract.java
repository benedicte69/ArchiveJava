package com.example.bened.lawnmower.data;

import android.provider.BaseColumns;

/*Outer Contract Class*/
public final class LawnMowerContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LawnMowerContract() {

    }

    /**
     * Inner class that defines the table contents: name of the table and its column.
     */
    public static abstract class LawnMowerEntry implements BaseColumns {

        //mower general information
        public static final String TABLE_NAME = "mowers";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MOWER_CATEGORY = "main_category";
        public static final String COLUMN_MOWER_SUB_CATEGORY = "sub_category";
        public static final String COLUMN_MOWER_NAME = "mower_name";
        public static final String COLUMN__SERIES_NAME = "series_name";

        //price and stock
        public static final String COLUMN_MOWERS_PRICE = "price";
        public static final String COLUMN_MOWERS_QUANTITY = "quantity";

        //suppliers info
        public static final String COLUMN_MOWERS_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_MOWERS_SUPPLIER_COUNTRY = "supplier_country";
        public static final String COLUMN_MOWERS_SUPPLIER_PHONE = "supplier_phone";

    }
}
