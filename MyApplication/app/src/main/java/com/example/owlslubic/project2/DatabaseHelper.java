package com.example.owlslubic.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

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
    public static final String COL_IMAGE = "image";
    public static final String COL_PRICE = "price";

    public static final String SHOPPING_CART_TABLE = "shopping_cart_table";
    public static final String COL_ITEM_ID = "_id";
    public static final String COL_PLANT_REF_ID = "plant_id";
    public static final String COL_QUANTITY = "quantity";


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
        db.execSQL(SQL_CREATE_SHOPPING_CART_TABLE);
        Log.d("AddData", "Data added!");
        insertPlantData();


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_PLANT_INFO_TABLE);
        db.execSQL(SQL_DELETE_SHOPPING_CART_TABLE);
    }

    //create tables
    private static final String SQL_CREATE_PLANT_INFO_TABLE = "CREATE TABLE " +
            PLANT_INFO_TABLE_NAME + " ("+
            COL_PLANT_ID +" INTEGER PRIMARY KEY, "+

                    /**HOW TO SET THIS ID TO INCEREMENT?**/

            COL_COMMON_NAME+" TEXT, "+
            COL_LATIN_NAME+" TEXT, "+
            COL_PLANT_TYPE+" TEXT, "+
            COL_DESCRIPTION+" TEXT, "+
            COL_IMAGE+" INT, "+
            COL_PRICE+" DOUBLE)";
    private static final String SQL_CREATE_SHOPPING_CART_TABLE = "CREATE TABLE " +
            SHOPPING_CART_TABLE+" ("+
            COL_ITEM_ID+" INTEGER PRIMARY KEY, "+
            COL_PLANT_REF_ID+" INTEGER, "+
            COL_QUANTITY+" INT, "+
            "FOREIGN KEY ("+COL_PLANT_REF_ID+") REFERENCES "+PLANT_INFO_TABLE_NAME+"("+COL_PLANT_ID+") )";

    private static final String SQL_DELETE_PLANT_INFO_TABLE = "DROP TABLE IF EXISTS "+PLANT_INFO_TABLE_NAME;
    private static final String SQL_DELETE_SHOPPING_CART_TABLE = "DROP TABLE IF EXISTS "+SHOPPING_CART_TABLE;


    //instantiate the different plants, insert them into the table

    /**WHAT'S UP WITH THE PLANT TYPE - FIX THIS BEFORE MOVING ON**/

    public void insertPlantData(){
        Plant wisteria = new Plant(R.string.wisteria_common_name,R.string.wisteria_latin_name,Vine, R.string.wisteria_description,R.drawable.wisteria,19.99);
        Plant knotweed = new Plant(R.string.japanese_knotweed_common_name, R.string.knotweed_latin_name, Weed, R.string.knotweed_description, R.drawable.knotweed,24.99);
        Plant ivy = new Plant(R.string.english_ivy_common_name, R.string.english_ivy_latin_name, Vine, R.string.english_ivy_description,R.drawable.english_ivy, 12.99);
        Plant ailanthus = new Plant(R.string.ailanthus_common_name, R.string.ailanthus_latin_name, Tree, R.string.ailanthus_description, R.drawable.ailanthus, 99.99);
        Plant garlicMustard = new Plant(R.string.garlic_mustard_common_name, R.string.garlic_mustard_latin_name, Weed, R.string.garlic_mustard_description, R.drawable.garlic_mustard,10.98);
        Plant daylily = new Plant(R.string.daylily_common_name, R.string.daylily_latin_name, Angiosperm, R.string.daylily_description, R.drawable.daylily, 21.99);
        Plant kudzu = new Plant(R.string.kudzu_common_name, R.string.kudzu_latin_name, Vine, R.string.kudzu_description, R.drawable.kudzu, 110.00);
        Plant paulownia = new Plant(R.string.paulownia_common_name, R.string.paulownia_latin_name, Tree, R.string.paulownia_description, R.drawable.paulownia, 99.01);
        Plant bittersweet = new Plant(R.string.oriental_bittersweet_common_name, R.string.oriental_bittersweet_latin_name, Vine, R.string.oriental_bittersweet_description, R.drawable.bittersweet, 82.99);
        Plant bamboo = new Plant(R.string.bamboo_common_name, R.string.bamboo_latin_name, Grass, R.string.bamboo_description, R.drawable.bamboo, 20.00);

        insertPlantTableRow(wisteria);
        insertPlantTableRow(knotweed);
        insertPlantTableRow(ivy);
        insertPlantTableRow(ailanthus);
        insertPlantTableRow(garlicMustard);
        insertPlantTableRow(daylily);
        insertPlantTableRow(kudzu);
        insertPlantTableRow(paulownia);
        insertPlantTableRow(bittersweet);
        insertPlantTableRow(bamboo);
    }

    public void insertPlantTableRow(Plant plant){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COMMON_NAME, plant.getmCommonName());
        values.put(COL_LATIN_NAME, plant.getmLatinName());
        values.put(COL_PLANT_TYPE, plant.getmPlantType().toString());
        values.put(COL_DESCRIPTION, plant.getmDescription());
        values.put(COL_IMAGE, plant.getmImage());
        values.put(COL_PRICE, plant.getmPrice());
        db.insertOrThrow(PLANT_INFO_TABLE_NAME,null,values);
        db.close();
    }


//WRITE METHOD TO GETPLANTS -- GET A CURSOR,
// THEN BEFORE MOVETONEXT, ADD EACH PLANT TO SOME LIST<PLANT>,
// AND USING A SWITCH OR SOMETHING, IF PLANT TYPE = VINE, CREATE NEW VINE OBJECT INSTEAD OF PLANT
// THEN RETURN THE LIST OF PLANTS
    //does this conflict with my insertData above since i already made new plant objects?


    //FOR SHOPPINGCART TABLE
    //writ a method that adds a row when the "add to cart" fab is clicked
    //write a method that deletes the table data when "checkout" fab is hit
    //write a method that deletes a single row of data from the table when the "x" on the item is hit

    
    
    
    /**don't look at these comments until you've done what's on your list from the 1:1**/
    
    
    //write helper methods to query the database for the searchview

    //make a method that inserts the data and call it in oncreate in this class




    //WRITE A METHOD for the mainact and the shoppingact recyclerviews: i will query the db and rather than return a cursor, i will go through and take each record in the cursor and turn it into an instance of Plant object, then put all those Plants into a collection list, and i will return the list rather than the cursor.
    //call it like populateRecyclerView or something



    //use onStop method to close the cursors
    //make sure to close database when done
}
