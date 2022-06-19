package com.android.Law.Data;

import static com.android.Law.Data.LawSQLiteContract.TableDocumentEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.util.Log;

import com.android.Law.models.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LawSQLiteDao {

    private SQLiteDatabase db;

    public LawSQLiteDao(Context context, boolean writeable) {
        if (writeable) {
            db = LawSQLiteDbHelper.getWriteableDatabase(context);
        } else {
            db = LawSQLiteDbHelper.getReadableDatabase(context);
        }
    }

    public LawSQLiteDao(Context context) {
        db = LawSQLiteDbHelper.getWriteableDatabase(context);
    }

    public Cursor getAllDocumentFollow(Context context) {
        //Query all student from data
        String query = "SELECT * FROM " + LawSQLiteContract.TableDocumentEntry.TABLE_NAME_FOLLOW;
        return db.rawQuery(query, null);
    }

    public Cursor getAllDocumentSeen(Context context) {
        //Query all student from data
        String query = "SELECT * FROM " + LawSQLiteContract.TableDocumentEntry.TABLE_NAME_SEEN;
        return db.rawQuery(query, null);
    }

    public List<Document> getListDocumentFollow(Context context){
        List<Document> documentList = new ArrayList<>();
        SQLiteDatabase db = LawSQLiteDbHelper.getReadableDatabase(context);
        Cursor cursor  = getAllDocumentFollow(context);
        if (cursor.moveToFirst()){
            do {
                Document document = new Document();
                document.setDocId(cursor.getString(0));
                document.setDocTitle(cursor.getString(1));
                document.setDocDescription(cursor.getString(2));
                document.setDocUrl(cursor.getString(3));
                documentList.add(document);
            }while (cursor.moveToNext());
        }
        db.close();
        Collections.reverse(documentList);
        return  documentList;
    }

    public List<Document> getListDocumentSeen(Context context){
        List<Document> documentList = new ArrayList<>();
        SQLiteDatabase db = LawSQLiteDbHelper.getReadableDatabase(context);
        Cursor cursor  = getAllDocumentSeen(context);
        if (cursor.moveToFirst()){
            do {
                Document document = new Document();
                document.setDocId(cursor.getString(0));
                document.setDocTitle(cursor.getString(1));
                document.setDocDescription(cursor.getString(2));
                document.setDocUrl(cursor.getString(3));
                documentList.add(document);
            }while (cursor.moveToNext());
        }
        db.close();
        Collections.reverse(documentList);
        return  documentList;
    }

    public static boolean addDocumentFollow(Context context,Document document){
        SQLiteDatabase db = LawSQLiteDbHelper.getWriteableDatabase(context);
        ContentValues values = new ContentValues();
        values.put(TableDocumentEntry.COLUMNS_DOC_ID, document.getDocId());
        values.put(TableDocumentEntry.COLUMNS_DOC_TITLE, document.getDocTitle());
        values.put(TableDocumentEntry.COLUMNS_DOC_DESCRIPTION, document.getDocDescription());
        values.put(TableDocumentEntry.COLUMNS_DOC_URL, document.getDocUrl());
        long row = db.insert(TableDocumentEntry.TABLE_NAME_FOLLOW,null,values);
        db.close();
        return  (row>0);
    }

    public static boolean addDocumentSeen(Context context,Document document){
        SQLiteDatabase db = LawSQLiteDbHelper.getWriteableDatabase(context);
        ContentValues values = new ContentValues();
        values.put(TableDocumentEntry.COLUMNS_DOC_ID, document.getDocId());
        values.put(TableDocumentEntry.COLUMNS_DOC_TITLE, document.getDocTitle());
        values.put(TableDocumentEntry.COLUMNS_DOC_DESCRIPTION, document.getDocDescription());
        values.put(TableDocumentEntry.COLUMNS_DOC_URL, document.getDocUrl());
        long row = db.insert(TableDocumentEntry.TABLE_NAME_SEEN,null,values);
        db.close();
        return  (row>0);
    }

    public boolean deleteDocumentFollow(Context context,String docId){
        SQLiteDatabase db = LawSQLiteDbHelper.getWriteableDatabase(context);
        int row = db.delete(TableDocumentEntry.TABLE_NAME_FOLLOW,TableDocumentEntry.COLUMNS_DOC_ID+"=?",new String[] {docId});
        return (row>0);
    }

    public boolean deleteDocumentSeen(Context context,String docId){
        SQLiteDatabase db = LawSQLiteDbHelper.getWriteableDatabase(context);
        int row = db.delete(TableDocumentEntry.TABLE_NAME_SEEN,TableDocumentEntry.COLUMNS_DOC_ID+"=?",new String[] {docId});
        return (row>0);
    }

    public static boolean checkExistDocumentFollow(Context context,String docId){
        try {
            SQLiteDatabase db = LawSQLiteDbHelper.getWriteableDatabase(context);
            String query = " SELECT  * FROM " + TableDocumentEntry.TABLE_NAME_FOLLOW + " WHERE " + TableDocumentEntry.COLUMNS_DOC_ID + "=? ";
            Cursor cursor = db.rawQuery(query, new String[]{docId});
            try {
                if (cursor.getCount() > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException es) {
                Log.d("SQLiteDao", "checkExistDocumentFollow: "+es);
            }
        }catch (SQLiteReadOnlyDatabaseException e)
        {
            Log.d("SQLiteDao", "checkExistDocumentFollow: "+e);
        }
        return  false;
    }

    public static boolean checkExistDocumentSeen(Context context,String docId){
        try {
            SQLiteDatabase db = LawSQLiteDbHelper.getWriteableDatabase(context);
            String query = " SELECT  * FROM " + TableDocumentEntry.TABLE_NAME_SEEN + " WHERE " + TableDocumentEntry.COLUMNS_DOC_ID + "=? ";
            Cursor cursor = db.rawQuery(query, new String[]{docId});
            try {
                if (cursor.getCount() > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException es) {
                Log.d("SQLiteDao", "checkExistDocumentFollow: "+es);
            }
        }catch (SQLiteReadOnlyDatabaseException e)
        {
            Log.d("SQLiteDao", "checkExistDocumentFollow: "+e);
        }
        return  false;
    }

}
