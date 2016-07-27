package com.example.owlslubic.project2;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by owlslubic on 7/25/16.
 */
public class MainRvAdapter extends RecyclerView.Adapter<MainViewHolder> {

//    List<Plant> plantList;
    Context mContext;
    DatabaseHelper helper = DatabaseHelper.getInstance(mContext);

    public MainRvAdapter(Context context, List<Plant> plants) {
     //   plantList = plants;
        mContext = context;

    }

    //this sets the layout for what each item in the recyclerview should use
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main, parent, false);
        MainViewHolder mainViewHolder = new MainViewHolder(view);
        return mainViewHolder;
    }

    //this specifies the contents of each item in the recyclerview
    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.getPlantInfoAndSetToView(plantList.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DetailDialog detailDialog = new DetailDialog();
             //   detailDialog.launchDetailDialog(view.getContext(),position,plantList);
                detailDialog.launchDetailDialog(view.getContext(),position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    //the following method is included in the tutorial, although i have not seen it before
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView){
//        super.onAttachedToRecyclerView(recyclerView);
//    }


}
