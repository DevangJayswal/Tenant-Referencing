package com.example.devang.tenantreferencing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Devang on 6/13/2016.
 */
public class CountryDAO extends SQLiteOpenHelper {


    public static final String TAG = "CountryDAO";
    String tableName = "country";

    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DbHelper.colEnquiryEnquiryID,

    String create = "CREATE TABLE " + tableName + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT)";
    String query = "select * from " + tableName + "";
    String drop = "DROP TABLE IF EXISTS " + tableName + "";

    public CountryDAO(Context context) {
        super(context, AddEnquiryDAO.databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop);
        onCreate(db);

    }

    public boolean insertCountry(String countryName) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
//        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", countryName);
        result = db.insert(tableName, null, contentValues);
//        db.endTransaction();
//        db.close();

        if (result == -1)
            return false;
        else
            return true;


    }

    public ArrayList<String> getCountry() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        try {

//            db.beginTransaction();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String country = cursor.getString(cursor.getColumnIndex("name"));
                    list.add(country);
                }
            }
//            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            db.endTransaction();
//            db.close();
        }
        return list;
    }
}
