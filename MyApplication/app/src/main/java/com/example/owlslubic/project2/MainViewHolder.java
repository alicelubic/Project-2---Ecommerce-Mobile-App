package com.example.owlslubic.project2;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by owlslubic on 7/25/16.
 */
public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public CardView cardView;
    public TextView plantName;
    public ImageView plantPic;




    public MainViewHolder(View itemView) {
        super(itemView);

        cardView = (CardView) itemView.findViewById(R.id.cardview_main_activity);
        plantName = (TextView) itemView.findViewById(R.id.textview_name_card_main);
        plantPic = (ImageView) itemView.findViewById(R.id.imageview_card_main);

    }

    //method that takes in one instance of Plant as the argument, and extracts
    //info from it, then assigns that data to the specific views
    //you will call this method in onBind
    public void getPlantInfoAndSetToView(Plant plant){
        String commonName = plant.getmCommonName();
        int image = plant.getmImage();
        plantName.setText(commonName);
        plantPic.setImageResource(image);
    }


    @Override
    public void onClick(View view) {



    }
}
