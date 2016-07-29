package com.example.owlslubic.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owlslubic on 7/23/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //constants
    public static final String KEY = "key";

    public static final int DATABASE_VERSION = 7;
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

    public static final String[] PLANT_INFO_COLUMNS = {COL_PLANT_ID, COL_LATIN_NAME, COL_COMMON_NAME, COL_PLANT_TYPE};

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
            COL_PLANT_ID + " INTEGER PRIMARY KEY, " +
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


    public void insertPlantTableRow(Plant plant) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COMMON_NAME, plant.getmCommonName());
        values.put(COL_LATIN_NAME, plant.getmLatinName());
        values.put(COL_PLANT_TYPE, plant.getPlantType());
        values.put(COL_DESCRIPTION, plant.getmDescription());
        values.put(COL_IMAGE, plant.getmImage());
        values.put(COL_PRICE, plant.getmPrice());
        db.insertOrThrow(PLANT_INFO_TABLE_NAME, null, values);
        db.close();
    }


    public boolean doesDataAlreadyExistInDb() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COL_PLANT_ID + " FROM " + PLANT_INFO_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else if (cursor.getCount() > 10) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return true;
        }
    }

    public void clearPlantTableData() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + PLANT_INFO_TABLE_NAME);
        db.close();
    }


    //instantiate the different plants, insert them into the table
    public void insertPlantData() {
//        if we were still talkin bout using the R.strings thing:
//        int resouceId = context.getResources().getIdentifier("bamboo_common_name","string",context.getPackageName());
//        String bamboo_common_name = context.getString(resouceId);


        if (!doesDataAlreadyExistInDb()) {

            Plant wisteria = new Vine(1, "Chinese Wisteria", "Wisteria sinensis", "This deciduous woody vine is capable of growing to a height of 40 ft. Good luck getting that down!", R.drawable.wisteria, 19.99);
            Plant knotweed = new Weed(2, "Japanese Knotweed", "Reynoutria japonica", "This weed is classified as an invasive species in 39 of the 50 United States, so why not add yours to the list!", R.drawable.knotweed, 24.99);
            Plant ivy = new Vine(3, "English Ivy", "Hedera helix", "English Ivy is a rampant, clinging evergreen vine that has been known to crowd out and choke other plants, creating an \"ivy desert\"", R.drawable.ivy, 12.99);
            Plant ailanthus = new Tree(4, "Tree of Heaven", "Ailanthus altissima", "This tree resprouts vigorously when cut, your neighbors' efforts to suppress this beast will drive them crazy for decades to come!", R.drawable.ailanthus, 99.99);
            Plant garlicMustard = new Weed(5, "Garlic Mustard", "Alliaria petiolata", "This stinky weed cannot be killed.", R.drawable.garlicmustard, 10.98);
            Plant daylily = new Angiosperm(6, "Orange Daylily", "Hemerocallis fulva", "Its beautiful bright orange flowers will dazzle your neighbors, who will never suspect that these plants behave just as maddeningly as any perennial weed.", R.drawable.daylily, 21.99);
            Plant kudzu = new Vine(7, "Kudzu", "Pueraria lobata", "A lovely vine that will take over and deprive all other plants of resources", R.drawable.kudzu, 110.00);
            Plant paulownia = new Tree(8, "Princess Tree", "Paulownia tomentosa", "Princess Tree is an showy and aggressive ornamental tree, so it should get along great with your neighbors!", R.drawable.paulownia, 99.01);
            Plant bittersweet = new Vine(9, "Oriental Bittersweet", "Celastrus orbiculatus", "A vine that grows aggressively, smothering trees, shrubs, and other irritating entities like your neighbors. Just kidding.", R.drawable.oriental_bittersweet, 82.99);
            Plant bamboo = new Grass(10, "Bamboo", "Bambusoidae", "Bamboo is a giant grass, and a giant pain in the ass. Once established, it is impossible to control, for each sprout that shoots up from the ground can grow 12 inches a day. That'll teach 'em.", R.drawable.bamboo, 20.00);

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
                        Angiosperm angio = new Angiosperm(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(angio);
                        break;
                    case "Grass":
                        Grass grass = new Grass(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(grass);
                        break;
                    case "Tree":
                        Tree tree = new Tree(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(tree);
                        break;
                    case "Vine":
                        Vine vine = new Vine(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(vine);
                        break;
                    case "Weed":
                        Weed weed = new Weed(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                        plantList.add(weed);
                        break;
                }
                Log.d(KEY, cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)) + " these are the plant ids getting added to plantlist");
                cursor.moveToNext();
            }
        }
        cursor.close();
        return plantList;
    }

    //helper method adds a row to shopping cart table when "add to cart" fab is clicked
    public void addToCart(Plant plant) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        int plantId = plant.getId();
        values.put(COL_PLANT_REF_ID, plantId);
        values.put(COL_QUANTITY, 1); //then somewhere make a method that will change this value when increment buttons are hit
        long id = db.insertOrThrow(SHOPPING_CART_TABLE, null, values);
     //   Log.v(KEY, "Data added to shopping cart table");
        db.close();
    }

//    public Plant getPlantObjectByName(String name){
//        SQLiteDatabase db = getReadableDatabase();
//        String type = plant.getPlantType();
//        String query = "SELECT * FROM "+PLANT_INFO_TABLE_NAME+" WHERE " + COL_COMMON_NAME + " LIKE ? "+name;
//        Cursor cursor = db.rawQuery(query,null);
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//                switch (cursor.getString(cursor.getColumnIndex(COL_PLANT_TYPE))) {
//                    case "Angiosperm":
//                        Plant angio = new Angiosperm(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
//                        break;
//                    case "Grass":
//                        Plant grass = new Grass(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
//                        break;
//                    case "Tree":
//                        Plant tree = new Tree(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
//                        break;
//                    case "Vine":
//                        Plant vine = new Vine(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
//                        break;
//                    case "Weed":
//                        Plant weed = new Weed(cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)), cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), (cursor.getString(cursor.getColumnIndex(COL_LATIN_NAME))), cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
//                        break;
//                }
//                Log.d(KEY, cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID)) + " these are the plant ids getting added to plantlist");
//                cursor.moveToNext();
//            }
//        }
//        cursor.close();
//        return plant;
//
//    }

    public void deleteItemFromCart(CartObject item) {
        int id = getPlantIdFromCartObject(item);
        SQLiteDatabase db = getWritableDatabase();

        String selection = COL_PLANT_REF_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        db.delete(SHOPPING_CART_TABLE, selection, selectionArgs);
        db.close();

        Log.d(KEY, "deleted item from cart table");
    }

    //this is for use in the deletefromcart method
    public int getPlantIdFromCartObject(CartObject item) {
        int id = 0;
        //  String name = item.getmName();
        //trying this with the image id because there wont be a space? -- worked
        String imageId = String.valueOf(item.getmImage());
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COL_PLANT_ID + " FROM " + PLANT_INFO_TABLE_NAME +
                " WHERE " + COL_IMAGE + " LIKE " + imageId;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = cursor.getInt(cursor.getColumnIndex(COL_PLANT_ID));
                cursor.moveToNext();
            }
        }
       // db.close();
        return id;
    }


    //method that deletes the table data when "checkout" fab is hit
    public void clearCartTableUponCheckout() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + SHOPPING_CART_TABLE);
        db.close();
    }

    //turns cart table into a list of cart objects to pass to the rv adapter
    public ArrayList<CartObject> getCartItemsAsObjects() {
        ArrayList<CartObject> cartList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COL_COMMON_NAME + "," + COL_QUANTITY + "," + COL_IMAGE + "," + COL_PRICE +
                " FROM " + PLANT_INFO_TABLE_NAME + " JOIN " + SHOPPING_CART_TABLE + " ON " +
                SHOPPING_CART_TABLE + "." + COL_PLANT_REF_ID + " = " + PLANT_INFO_TABLE_NAME + "." + COL_PLANT_ID;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                CartObject item = new CartObject(cursor.getString(cursor.getColumnIndex(COL_COMMON_NAME)), cursor.getDouble(cursor.getColumnIndex(COL_PRICE)), cursor.getInt(cursor.getColumnIndex(COL_QUANTITY)), cursor.getInt(cursor.getColumnIndex(COL_IMAGE)));
                cartList.add(item);

                cursor.moveToNext();
            }
        }
        cursor.close();
        return cartList;

    }


    //FOR SEARCHABILITY
    public Cursor searchPlants(String query) {
        SQLiteDatabase db = getReadableDatabase();
        String where = " " + COL_COMMON_NAME + " LIKE ? OR " + COL_PLANT_TYPE + " LIKE ?";
        Cursor cursor = db.query(PLANT_INFO_TABLE_NAME,
                PLANT_INFO_COLUMNS,
                where,
                new String[]{"%" + query + "%", "%" + query + "%"},
                null, null, null);
      //  cursor.close();
        return cursor;
    }



    //to update cart table with quantity
    //get quanitity method also
    public int getQuantityFromTable(CartObject item){
        SQLiteDatabase db = getReadableDatabase();
        int quantity=0;
        int plantId = getPlantIdFromCartObject(item);
        String query = "SELECT "+ COL_QUANTITY+" FROM "+SHOPPING_CART_TABLE+" WHERE "+COL_PLANT_REF_ID+" = "+ plantId;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                quantity = cursor.getInt(cursor.getColumnIndex(COL_QUANTITY));
                Log.d(KEY,"the current quantity is "+quantity);
                cursor.moveToNext();
            }

            }
     //   cursor.close();
        return quantity;
    }



    public void increaseQty(CartObject item){
        SQLiteDatabase db = getWritableDatabase();
        int plantId = getPlantIdFromCartObject(item);
        int currentQty = getQuantityFromTable(item);
        String update = "UPDATE "+SHOPPING_CART_TABLE+" SET "+COL_QUANTITY+" = "+(currentQty+1)+" WHERE "+COL_PLANT_REF_ID+" = "+plantId;
        db.execSQL(update);
        db.close();
    }
    public void decreaseQty(CartObject item){
        SQLiteDatabase db = getWritableDatabase();
        int plantId = getPlantIdFromCartObject(item);
        int currentQty = getQuantityFromTable(item);
        String update = "UPDATE "+SHOPPING_CART_TABLE+" SET "+COL_QUANTITY+" = "+(currentQty-1)+" WHERE "+COL_PLANT_REF_ID+" = "+plantId;
        db.execSQL(update);
        db.close();
    }

}

//currentqty= getQuantityFromTable(item), currentqty+1=newquantity, return newquantity
