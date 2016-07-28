package com.example.owlslubic.project2;

/**
 * Created by owlslubic on 7/25/16.
 */
public class Vine extends Plant {
    public Vine(int id, String commonName, String latinName, String description, int image, double price) {
        super(id, commonName, latinName, description, image, price);
    }

    @Override
    public String getPlantType() {
        return "Vine";
    }
}
