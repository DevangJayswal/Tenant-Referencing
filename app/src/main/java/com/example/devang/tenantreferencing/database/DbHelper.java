package com.example.devang.tenantreferencing.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Devang on 6/8/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";
    private static final int databaseVersion = 1;
    public static final String databaseName = "tenantReferencing.db";

    // Enquiry table
    public static final String tableEnquiry = "Enquiry";
    public static final String colEnquiryEnquiryID = "enquiryID";
    public static final String colEnquiryNoOfTenants = "noOfTenants";
    public static final String colEnquiryAddress1 = "address1";
    public static final String colEnquiryAddress2 = "address2";
    public static final String colEnquiryTown = "town";
    public static final String colEnquiryPostcode = "postcode";
    public static final String colEnquiryCountry = "country";
    public static final String colEnquiryTenancyStartDate = "tenancyStartDate";
    public static final String colEnquiryProduct = "product";
    public static final String colEnquiryTenancyTerm = "tenancyTerm";
    //SQL statement for creating
    public static final String sqlCreateTableEnquiry = "CREATE TABLE " +
            tableEnquiry + " (" +
            colEnquiryEnquiryID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " "+ colEnquiryNoOfTenants +" INTEGER, " +
            "" + colEnquiryAddress1 +  " TEXT, " +
            ""+ colEnquiryAddress2 +" TEXT, " +
            ""+ colEnquiryTown +" TEXT, " +
            ""+ colEnquiryPostcode +" TEXT, " +
            ""+ colEnquiryCountry +" INTEGER," +
            ""+ colEnquiryTenancyStartDate +" date, " +
            ""+ colEnquiryProduct +" INTEGER, " +
            ""+ colEnquiryTenancyTerm +" INTEGER)";



    public DbHelper(Context context) {
        super(context, databaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //backup query
    //"CREATE TABLE AddEnquiryDAO (enquiryID INTEGER PRIMARY KEY AUTOINCREMENT," +
    //" noOfTenants INTEGER, address1 TEXT, address2 TEXT, town TEXT, country INTEGER," +
      //      "tenancyStartDate date, product INTEGER, tenancyTerm INTEGER)"

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateTableEnquiry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to "+ newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + tableEnquiry);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANIES);

        // recreate the tables
        onCreate(db);
    }




//    public boolean insertData(String noOfTenants, String address1, String address2,
//                              String town, int country, String tenancyStartDate, int product, int tenancyTerm) {
//        // going to create database and table
//        //SQLiteDatabase db = this.getClass()
//        SQLiteDatabase db = this.getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put("noOfTenants", noOfTenants);
////        contentValues.put("address1", address1);
////        contentValues.put("address2", address2);
////        contentValues.put("address2", address2);
////        contentValues.put("town", town);
////        contentValues.put("tenancyStartDate", tenancyStartDate);
////        contentValues.put("product", product);
////        contentValues.put("tenancyTerm", tenancyTerm);
////
////        long result = db.insert("Student", null, contentValues);
////        if (result == -1)
////            return false;
////        else
////            return true;
//        return true;
//    }


}
