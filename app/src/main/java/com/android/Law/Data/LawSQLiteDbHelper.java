package com.android.Law.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class LawSQLiteDbHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "LawSQLite.db";
    private static final int DATABASE_VERSION = 1;

    public LawSQLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static public SQLiteDatabase getReadableDatabase(Context context) {
        //Init database helper, and database content to communicate with sqlite database
        LawSQLiteDbHelper mDbHelper = new LawSQLiteDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db;
    }

    static public SQLiteDatabase getWriteableDatabase(Context context) {
        //Init database helper, and database content to communicate with sqlite database
        LawSQLiteDbHelper mDbHelper = new LawSQLiteDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db;
    }
}
