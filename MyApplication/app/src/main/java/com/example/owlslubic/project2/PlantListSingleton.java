package com.example.owlslubic.project2;

import java.util.ArrayList;

/**
 * Created by owlslubic on 7/27/16.
 */
public class PlantListSingleton {
    //DEFINITELY NOT DOING THIS, BUT IM AFRAID TO DELETE ANYTHING

    //ok im going to experiment and see if just having one isntance of my list will be good or nah

    private static PlantListSingleton plants = null;
    private static ArrayList<Plant> plantList;

    private PlantListSingleton() {
        plantList = new ArrayList<>();

    }

    public static PlantListSingleton getInstance(){
        if(plants == null){
            plants = new PlantListSingleton();
        }
        return plants;
    }
}
