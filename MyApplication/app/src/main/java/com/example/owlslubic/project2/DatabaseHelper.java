package com.example.owlslubic.project2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by owlslubic on 7/23/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //constants
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "plant_store.db";

    public static final String PLANT_INFO_TABLE_NAME = "plant_table";
    public static final String COL_PLANT_ID = "_id";
    public static final String COL_LATIN_NAME = "latin_name";
    public static final String COL_COMMON_NAME = "common_name";
    public static final String COL_PLANT_TYPE = "plant_type";
    public static final String COL_DESCRIPTION = "description";
    //save all these plant data in @string, not hardcoded

    public static final String STORE_PRODUCT_TABLE = "store_table";
    public static final String COL_PRODUCT_ID = "_id";
    public static final String COL_PLANT_REF_ID = "plant_id";
    public static final String COL_PRICE = "price";

//    public static final String COL_QUANTITY = "quantity"; not sure if I wanna use this

    private static DatabaseHelper sInstance;

    //constructor
    private DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //getInstance helper method
    public static DatabaseHelper getInstance(Context context){
        if (sInstance == null){
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PLANT_INFO_TABLE);
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_PLANT_INFO_TABLE);
        db.execSQL(SQL_DELETE_STORE_PRODUCT_TABLE);
    }

    //create tables
    private static final String SQL_CREATE_PLANT_INFO_TABLE = "CREATE TABLE " +
            PLANT_INFO_TABLE_NAME + " ("+
            COL_PLANT_ID +" INTEGER PRIMARY KEY, "+
                    /**HOW TO SET THIS ID TO INCEREMENT?**/
            COL_LATIN_NAME+" TEXT, "+
            COL_COMMON_NAME+" TEXT, "+
            COL_PLANT_TYPE+" TEXT, "+
            COL_DESCRIPTION+" TEXT)";
    private static final String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " +
            STORE_PRODUCT_TABLE+" ("+
            COL_PRODUCT_ID+" INTEGER PRIMARY KEY, "+
            COL_PLANT_REF_ID+" INTEGER, "+
            COL_PRICE+" INTEGER, "+
            "FOREIGN KEY ("+COL_PLANT_REF_ID+") REFERENCES "+PLANT_INFO_TABLE_NAME+"("+COL_PLANT_ID+") )";
    private static final String SQL_DELETE_PLANT_INFO_TABLE = "DROP TABLE IF EXISTS "+PLANT_INFO_TABLE_NAME;
    private static final String SQL_DELETE_STORE_PRODUCT_TABLE = "DROP TABLE IF EXISTS "+STORE_PRODUCT_TABLE;



    //write helper methods to query the database for the searchview

    //use onStop method to close the cursors
}
