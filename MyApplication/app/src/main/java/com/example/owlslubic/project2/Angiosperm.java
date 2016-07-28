package com.example.owlslubic.project2;

import android.graphics.drawable.Drawable;

/**
 * Created by owlslubic on 7/25/16.
 */
public class Angiosperm extends Plant {
    public Angiosperm(int id, String commonName, String latinName, String description, int image, double price) {
        super(id, commonName, latinName, description, image, price);
    }

    @Override
    public String getPlantType() {
        return "Angiosperm";
    }
}
