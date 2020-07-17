package com.example.dataaccessdemo;

import android.provider.BaseColumns;

public class TableDetails {

    private TableDetails() { }

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "Contacts";
        public static final String COLUMN_FIRST_NAME = "First_Name";
        public static final String COLUMN_LAST_NAME = "Last_Name";
        public static final String COLUMN_CONTACT_NUMBER = "Contact_Number";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                    Entry._ID + " INTEGER PRIMARY KEY, " +
                    Entry.COLUMN_FIRST_NAME + " TEXT, " +
                    Entry.COLUMN_LAST_NAME + " TEXT, " +
                    Entry.COLUMN_CONTACT_NUMBER + " TEXT " +
            ")";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;

}
