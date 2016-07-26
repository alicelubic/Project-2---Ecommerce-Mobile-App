package com.example.owlslubic.project2;

import android.graphics.drawable.Drawable;

/**
 * Created by owlslubic on 7/22/16.
 */
public abstract class Plant {//don't think i need to attach the id here but maybe
    String mCommonName, mLatinName, mDescription;
    int mImage;
    double mPrice;
public Plant(String commonName, String latinName, String description, int image, double price) {
        mCommonName = commonName;
        mLatinName = latinName;
        mDescription = description;
     //   mPlantType = plantType;
        mImage = image;
        mPrice = price;
    }

    public abstract String getPlantType();
    //returns R.string.vine or whatever


    //getter methods that you will use to setText of a text view etc
    public String getmCommonName() {
        return mCommonName;
    }


    public String getmLatinName() {
        return mLatinName;
    }


    public String getmDescription() {
        return mDescription;
    }


//    public Plant getmPlantType() {
//        return mPlantType;
//    }



    public int getmImage() {
        return mImage;
    }


    public double getmPrice() {
        return mPrice;
    }



}
