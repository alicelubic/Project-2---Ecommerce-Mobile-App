package com.example.owlslubic.project2;

import java.util.ArrayList;

/**
 * Created by owlslubic on 7/27/16.
 */
public class PlantListSingleton {
    //ok im going to experiment and see if just having one isntance of my list will be good or nah

    private static PlantListSingleton plants = null;
    private static ArrayList<Plant> plantList;

    private PlantListSingleton() {
        plantList = new ArrayList<>();
    }

    public static CartSingleton getInstance(){
        if(cart == null){
            cart = new CartSingleton();
        }
        return cart;
    }
}
