package com.example.owlslubic.project2;

import android.graphics.drawable.Drawable;

/**
 * Created by owlslubic on 7/25/16.
 */
public class Angiosperm extends Plant {
    public Angiosperm(String commonName, String latinName, String description, int image, double price) {
        super(commonName, latinName, description, image, price);
    }

    @Override
    public String getPlantType() {
        return "Angiosperm";
    }
}
