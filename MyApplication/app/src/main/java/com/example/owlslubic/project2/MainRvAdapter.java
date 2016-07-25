package com.example.owlslubic.project2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by owlslubic on 7/25/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<MainViewHolder> {

    //here I will declare the object list to be displayed, and I will set the adapter to that list
    //something like List<Plant> plants;
    //MainRvAdapter(List<Plant> plants){this.plants = plants;}


    //this sets the layout for what each item in the recyclerview should use
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main, parent, false);
        MainViewHolder mainViewHolder = new MainViewHolder(view);
        return mainViewHolder;
    }

    //this specifies the contents of each item in the recyclerview
    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
//this is where i will include a collection of my plants -- i will call a method from the dbHelper that returns a list of Plant objects
    }

    @Override
    public int getItemCount() {
        return 0;
        //return listname.size();
    }

   //the following method is included in the tutorial, although i have not seen it before
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
