package com.example.owlslubic.project2;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by owlslubic on 7/26/16.
 */
public class DetailDialog {
    public static final String KEY = "key";

    public TextView commonNameDetail, latinNameDetail, priceDetail, descriptionDetail;
    public ImageView picDetail;

    public void launchDetailDialog(final Context context, final int position,final List<Plant> plantList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogLayout = inflater.inflate(R.layout.dialog_product_detail, null);
        builder.setView(dialogLayout);

        final AlertDialog dialog = builder.create();
        dialog.show();
        ImageButton button = (ImageButton) dialogLayout.findViewById(R.id.button_cancel_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        FloatingActionButton addToCart = (FloatingActionButton) dialogLayout.findViewById(R.id.fab_detail_dialog);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added to cart!", Toast.LENGTH_SHORT).show();
                DatabaseHelper helper = DatabaseHelper.getInstance(context);
                helper.addToCart(plantList.get(position));
                dialog.dismiss();
            }
        });

        commonNameDetail = (TextView) dialog.findViewById(R.id.textview_common_name);
        latinNameDetail = (TextView) dialog.findViewById(R.id.textview_latin_name);
        priceDetail = (TextView) dialog.findViewById(R.id.textview_price);
        descriptionDetail = (TextView) dialog.findViewById(R.id.textview_description_detail);
        picDetail = (ImageView) dialog.findViewById(R.id.imageview_dialog_detail);

        setDetailDialogInfo(plantList.get(position));
    }


    public void setDetailDialogInfo(Plant plant) {
        commonNameDetail.setText(plant.getmCommonName());
        latinNameDetail.setText(plant.getmLatinName());
        priceDetail.setText("$" + plant.getmPrice());
        descriptionDetail.setText(plant.getmDescription());
        picDetail.setImageResource(plant.getmImage());
    }


}
