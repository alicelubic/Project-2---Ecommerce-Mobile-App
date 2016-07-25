package com.example.owlslubic.project2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by owlslubic on 7/25/16.
 */
public class MainViewHolder extends RecyclerView.ViewHolder{
    CardView mCardView;
    TextView mPlantName;
    ImageView mPlantPic;

    public MainViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.cardview_main_activity);
        mPlantName = (TextView) itemView.findViewById(R.id.textview_name_card_main);
        mPlantPic = (ImageView) itemView.findViewById(R.id.imageview_card_main);
    }
}
