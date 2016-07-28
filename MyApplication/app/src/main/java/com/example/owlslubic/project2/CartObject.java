package com.example.owlslubic.project2;

/**
 * Created by owlslubic on 7/27/16.
 */
public class CartObject {
    String mName;
    double mPrice;
    int mQuantity, mImage;

    public CartObject(String name, double price, int quantity, int image){
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




}
