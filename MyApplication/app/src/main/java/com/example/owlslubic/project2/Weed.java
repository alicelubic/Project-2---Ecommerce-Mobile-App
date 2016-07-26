package com.example.owlslubic.project2;

/**
 * Created by owlslubic on 7/25/16.
 */
public class Weed extends Plant {

    public Weed(int commonName, int latinName, int description, int image, double price) {
        super(commonName, latinName, description, image, price);
    }

    @Override
    public int getPlantType() {
        return R.string.weed;
    }
}
