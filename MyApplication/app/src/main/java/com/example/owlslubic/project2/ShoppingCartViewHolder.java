package com.example.owlslubic.project2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by owlslubic on 7/25/16.
 */
public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
    CardView mCardView;
    TextView mPrice, mName, mQuantity;
    ImageView mPlantImage;
    ImageButton mDecrement, mIncrement, mRemove;


    public ShoppingCartViewHolder(View itemView) {
        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.cardview_cart);
        mPrice = (TextView) itemView.findViewById(R.id.textview_price_card_cart);
        mName = (TextView) itemView.findViewById(R.id.textview_name_card_cart);
        mQuantity = (TextView) itemView.findViewById(R.id.textview_quantity_cart);
        mPlantImage = (ImageView) itemView.findViewById(R.id.imageview_card_cart);
        mDecrement = (ImageButton) itemView.findViewById(R.id.imagebutton_decrement);
        mIncrement = (ImageButton) itemView.findViewById(R.id.imagebutton_increment);
        mRemove = (ImageButton) itemView.findViewById(R.id.imagebutton_remove_item);

        /**is this where i can set onclicklisteners and stuff?**/


    }

    public void getAndSetPlantInfoToShoppingCartCardView(Plant plant) {
        String commonName = plant.getmCommonName();
        double price = plant.getmPrice();
        int image = plant.getmImage();
        /**int quantity = plant. to get the quantity - gonna have to write a method that queries joined tables*/
        Log.v("cart", "it got and sot plant info to the cart cardview");


        mName.setText(commonName);
        mPlantImage.setImageResource(image);
        mPrice.setText(String.valueOf(price));
//        mQuantity.setText(quantity);
    }



}
