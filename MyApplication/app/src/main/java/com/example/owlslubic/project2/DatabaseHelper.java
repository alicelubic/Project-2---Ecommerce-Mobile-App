package com.example.owlslubic.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //getInstance helper method
    public static DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PLANT_INFO_TABLE);
        db.execSQL(SQL_CREATE_SHOPPING_CART_TABLE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_PLANT_INFO_TABLE);
        db.execSQL(SQL_DELETE_SHOPPING_CART_TABLE);
    }

    //create tables
    private static final String SQL_CREATE_PLANT_INFO_TABLE = "CREATE TABLE " +
            PLANT_INFO_TABLE_NAME + " (" +
            COL_PLANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_COMMON_NAME + " TEXT, " +
            COL_LATIN_NAME + " TEXT, " +
            COL_PLANT_TYPE + " TEXT, " +
            COL_DESCRIPTION + " TEXT, " +
            COL_IMAGE + " INT, " +
            COL_PRICE + " DOUBLE)"; //making price a double rather than text because i'm going to need to manipulate it as quantity changes
    private static final String SQL_CREATE_SHOPPING_CART_TABLE = "CREATE TABLE " +
            SHOPPING_CART_TABLE + " (" +
            COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_PLANT_REF_ID + " INTEGER, " +
            COL_QUANTITY + " INT, " +
            "FOREIGN KEY (" + COL_PLANT_REF_ID + ") REFERENCES " + PLANT_INFO_TABLE_NAME + "(" + COL_PLANT_ID + ") )";

    private static final String SQL_DELETE_PLANT_INFO_TABLE = "DROP TABLE IF EXISTS " + PLANT_INFO_TABLE_NAME;
    private static final String SQL_DELETE_SHOPPING_CART_TABLE = "DROP TABLE IF EXISTS " + SHOPPING_CART_TABLE;



    //the Plant Table methods all seem to work

    public void insertPlantTableRow(Plant plant) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COMMON_NAME, plant.getmCommonName());
        values.put(COL_LATIN_NAME, plant.getmLatinName());
        values.put(COL_PLANT_TYPE, plant.getPlantType()); //this is the abstract method that will return the string res name (int) of each subclass object type
        values.put(COL_DESCRIPTION, plant.getmDescription());
        values.put(COL_IMAGE, plant.getmImage());
        values.put(COL_PRICE, plant.getmPrice());
        db.insertOrThrow(PLANT_INFO_TABLE_NAME, null, values);
        db.close();
    }

    //instantiate the different plants, insert them into the table
    public void insertPlantData() {
//        if we were still talkin bout using the R.strings thing:
//        int resouceId = context.getResources().getIdentifier("bamboo_common_name","string",context.getPackageName());
//        String bamboo_common_name = context.getString(resouceId);

        Plant wisteria = new Vine("Chinese Wisteria", "Wisteria sinensis", "This deciduous woody vine capable of growing to a height of 40 ft., good luck getting that down!", R.drawable.wisteria, 19.99);
        Plant knotweed = new Weed("Japanese Knotweed", "Reynourtria japonica", "This weed is classified as an invasive species in 39 of the 50 United States, so why not add yours to the list!", R.drawable.wisteria, 24.99);
        Plant ivy = new Vine("English Ivy", "Hedera helix", "English Ivy is a rampant, clinging evergreen vine that has been known to crowd out and choke other plants, creating an \"ivy desert\"", R.drawable.wisteria, 12.99);
        Plant ailanthus = new Tree("Tree of Heaven", "Ailanthus altissima", "This tree resprouts vigorously when cut, your neighbors' efforts to suppress this beast will drive them crazy for decades to come!", R.drawable.wisteria, 99.99);
        Plant garlicMustard = new Weed("Garlic Mustard", "Alliaria petiolata", "This stinky weed cannot be killed.", R.drawable.wisteria, 10.98);
        Plant daylily = new Angiosperm("Orange Daylily", "Hemerocallis fulva", "Its beautiful bright orange flowers will dazzle your neighbors, who will never suspect that these plants behave just as maddeningly as any perennial weed.", R.drawable.wisteria, 21.99);
        Plant kudzu = new Vine("Kudzu", "Pueraria lobata", "A lovely vine that will take over and deprive all other plants of resources", R.drawable.wisteria, 110.00);
        Plant paulownia = new Tree("Princess Tree", "Paulownia tomentosa", "Princess Tree is an showy and aggressive ornamental tree, so it should get along great with your neighbors!", R.drawable.wisteria, 99.01);
        Plant bittersweet = new Vine("Oriental Bittersweet", "Celastrus orbiculatus", "A vine that grows aggressively, smothering trees, shrubs, and other irritating entities like your neighbors. Just kidding.", R.drawable.wisteria, 82.99);
        Plant bamboo = new Grass("Bamboo", "Bambusoidae", "Bamboo is a giant grass, and a giant pain in the ass. Once established, it is impossible to control, for each sprout that shoots up from the ground can grow 12 inches a day. That'll teach 'em.", R.drawable.wisteria, 20.00);

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




    //this method is for getting a list of Plant objects that I will be assigning to CardViews
    public List<Plant> getListOfAllPlants() {
        List<Plant> plantList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " +
                PLANT_INFO_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                switch (cursor.getString(cursor.getColumnIndex(COL_PLANT_TYPE))) {
                    case "Angiosperm":
                        Angiosperm angio = new Angiosperm(cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(angio);
                    case "Grass":
                        Grass grass = new Grass(cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(grass);
                    case "Tree":
                        Tree tree = new Tree(cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(tree);
                    case "Vine"://this won't make them all be "vine" will it?..check to see how this turns out
                        Vine vine = new Vine(cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(vine);
                    case "Weed":
                        Weed weed = new Weed(cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(weed);
                    default:
                        //do i need a default?
                }
                cursor.moveToNext();
            }
        }
        /**where do I close cursor?*/
        db.close();
        return plantList;
    }




    //FOR SHOPPING CART TABLE - have not tested these methods yet

    //helper method adds a row to shopping cart table when "add to cart" fab is clicked
    //takes in the plant object of whatever was displayed on the cardview/detaildialog -- so here,"plant" should be whatever item was clicked on
    public void addRowToCartTable(Plant plant){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PLANT_REF_ID, getPlantId(plant));
        values.put(COL_QUANTITY, 1); //then somewhere make a method that will change this value when increment buttons are hit
        db.insertOrThrow(SHOPPING_CART_TABLE,null,values);
        db.close();
    }

    //need to get plant id to add it to shopping cart table
    /**shouldnt it take in whatever item was clicked on?*/
    public Integer getPlantId(Plant plant){
        SQLiteDatabase db = getReadableDatabase();
        Integer result=0; //made this Integer because that's what the primary key is for that table...
        String plantName = plant.getmCommonName();
        String query = "SELECT "+
                COL_PLANT_ID+" FROM " +
                PLANT_INFO_TABLE_NAME +" WHERE "+COL_COMMON_NAME+" = "+ plantName;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                result+=(cursor.getColumnIndex(COL_PLANT_ID));
                cursor.moveToNext();
            }
        }
        cursor.close();//is this gonna fuck me up again like in that lab?
        return result;
    }


    //method that deletes a single row of data (representing an item) from the table when the "x" on the item is hit - this is independent of its quantity
    //this one doesn't feel right, i'm sure i'll hvae to change it
    public void deleteRowFromCartTableByPlantName(Plant plant){

        SQLiteDatabase db = getWritableDatabase();
        String selection = COL_COMMON_NAME + " = ?";
        String[] selectionArgs = new String[]{plant.getmCommonName()};
        db.delete(SHOPPING_CART_TABLE, selection, selectionArgs);
        db.close();
    }


    //method that deletes the table data when "checkout" fab is hit
    public void clearCartTableUponCheckout(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+SHOPPING_CART_TABLE);
        db.close();
    }













    //write helper methods to query the database for the searchview


    //use onStop method to close the cursors
    //make sure to close database when done
}
