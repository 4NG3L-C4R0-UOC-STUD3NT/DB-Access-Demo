package com.example.dataaccessdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactsDBHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "Contact_Details";
    private static final int DATABASE_VERSION = 1;

    public ContactsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableDetails.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TableDetails.DROP_TABLE);
        onCreate(db);
    }

}
