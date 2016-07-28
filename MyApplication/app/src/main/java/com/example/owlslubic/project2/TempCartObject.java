package com.example.owlslubic.project2;

/**
 * Created by owlslubic on 7/27/16.
 */
public class TempCartObject {
    String mName;
    double mPrice;
    int mQuantity, mImage;

    public TempCartObject(String name, double price, int quantity, int image){
        mName = name;
        mPrice = price;
        mQuantity=quantity;
        mImage=image;
    }

    public String getmName() {
        return mName;
    }

    public double getmPrice() {
        return mPrice;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public int getmImage() {
        return mImage;
    }
    //    int mId, mPlantId, mQuantity;
//    public TempCartObject(int id, int plantId, int quantity) {
//        mId=id;
//        mPlantId=plantId;
//        mQuantity=quantity;
//
//    }


    //shopping cart should hold list of Plant objects, not these
}
