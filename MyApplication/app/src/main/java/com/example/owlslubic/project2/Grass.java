package com.example.owlslubic.project2;

/**
 * Created by owlslubic on 7/25/16.
 */
public class Grass extends Plant {
    public Grass(int id, String commonName, String latinName, String description, int image, double price) {
        super(id, commonName, latinName, description, image, price);
    }

    @Override
    public String getPlantType() {
        return "Grass";
    }
}
