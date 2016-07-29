package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owlslubic on 7/25/16.
 */

public class ShoppingCartRvAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {
    public static final String KEY = "key";
    Context mContext;
    List<CartObject> cartList = new ArrayList<>();


    //constructor - adding list parameter
    public ShoppingCartRvAdapter(Context context, List<CartObject> cartList) {
        mContext = context;
        this.cartList = cartList;

    }

    //this sets the layout for what each item in the recyclerview should use
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shopping_cart, parent, false);
        ShoppingCartViewHolder viewHolder = new ShoppingCartViewHolder(view);
        return viewHolder;
    }


    //this specifies the contents of each item in the recyclerview
    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, final int position) {
        final CartObject item = cartList.get(position);
        holder.mName.setText(item.getmName());
        holder.mPrice.setText(String.valueOf(item.getmPrice()));
        holder.mQuantity.setText(String.valueOf(item.getmQuantity()));
        holder.mPlantImage.setImageResource(item.getmImage());

        //  Log.d(KEY, cartList.size()+ "this is the size of the cartlist upon being bound to the shopping cart");


        holder.mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this should remove from recyclerview and table
                removeByPosition(holder.getAdapterPosition());

                //   Log.d(KEY, "removeItemByPosition removed item from recyclerview from sc adapter");
                Toast.makeText(mContext, "Deleted from cart!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //quantity db and textview, price textview, and total textview should reflect this

                holder.mPrice.setText(String.valueOf(addToPrice(item.getmPrice())));
//                holder.mTotal.setText(String.valueOf());
                holder.mQuantity.setText(String.valueOf(addQuantity(item.getmQuantity())));
            }
        });
        holder.mDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mPrice.setText(String.valueOf(removeFromPrice(item.getmPrice())));
//                holder.mTotal.setText(String.valueOf());
                holder.mQuantity.setText(String.valueOf(removeQuantity(item.getmQuantity())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public void removeByPosition(int position) {
        DatabaseHelper.getInstance(mContext).deleteItemFromCart(cartList.get(position));
        cartList.remove(position);

        Log.d(KEY, cartList.size() + " this is the size of cart list after an item is removed");
        notifyItemRemoved(position);
    }




    //ok all of these methods need some sort of counter to keep track of what they are adding to or nah


    //method to affect price:

    public double addToPrice(double price){
       double newPrice = price + price;


       // item.getmQuantity
        //item.getmPrice()



        return newPrice;
    }

    //method to affect total:
    public double removeFromPrice(double price){
        double newPrice = price - price;
        return newPrice;
    }

//    //need some sort of counter to keep track
//    public double updateTotal(double price){
//        int newTotal = oldTotal + price;
//        return newTotal;
//    }
//


    //these must change in db as well

    //method to affect quantity:
    public int addQuantity(int quantity){
        return quantity + 1;
    }

    public int removeQuantity(int quantity){
        return quantity - 1;
    }

}


