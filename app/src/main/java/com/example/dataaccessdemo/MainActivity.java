package com.example.dataaccessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = findViewById(R.id.btnAddContent);
        addButton.setOnClickListener(this);
        Button seeButton = findViewById(R.id.btnSeeContent);
        seeButton.setOnClickListener(this);

        ContactsDBHelper contactsDBHelper = new ContactsDBHelper(this);
        database = contactsDBHelper.getWritableDatabase();
    }

    void addContent() {
        ContentValues values = new ContentValues();
        values.put(TableDetails.Entry.COLUMN_FIRST_NAME, "juan");
        values.put(TableDetails.Entry.COLUMN_LAST_NAME, "perez");
        values.put(TableDetails.Entry.COLUMN_CONTACT_NUMBER, "+91999999999");
        long newRowId = database.insert(TableDetails.Entry.TABLE_NAME, null, values);
    }

    void seeContent() {
        String[] projection = {
                TableDetails.Entry._ID,
                TableDetails.Entry.COLUMN_FIRST_NAME,
                TableDetails.Entry.COLUMN_LAST_NAME,
                TableDetails.Entry.COLUMN_CONTACT_NUMBER
        };
        String selection = TableDetails.Entry.COLUMN_FIRST_NAME + " = ?";
        String[] selectionArgs = { "juan" };
        String sortOrder = TableDetails.Entry.COLUMN_LAST_NAME + " DESC";

        Cursor cursor = database.query(
                TableDetails.Entry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(TableDetails.Entry.COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(TableDetails.Entry.COLUMN_LAST_NAME));
            String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(TableDetails.Entry.COLUMN_CONTACT_NUMBER));
            String id = cursor.getString(cursor.getColumnIndexOrThrow(TableDetails.Entry._ID));

            Log.d("[DB_ENTRY]", "seeContent: { " + id + "," + firstName + "," + lastName + "," + contactNumber + " }");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddContent:
                addContent();
                break;
            case R.id.btnSeeContent:
                seeContent();
                break;
        }
    }
}