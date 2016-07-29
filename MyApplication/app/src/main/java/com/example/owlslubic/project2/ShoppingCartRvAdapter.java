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
    DatabaseHelper helper = DatabaseHelper.getInstance(mContext);


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
        holder.mPrice.setText("$"+item.getmPrice());
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

                holder.mPrice.setText("$" + getNewPrice(item, holder));
//                holder.mTotal.setText(String.valueOf());
                helper.increaseQty(item);
                holder.mQuantity.setText(String.valueOf(String.valueOf(helper.getQuantityFromTable(item))));
            }
        });
        holder.mDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mPrice.setText("$" + getNewPrice(item, holder));
//                holder.mTotal.setText(String.valueOf());
                helper.decreaseQty(item);
                holder.mQuantity.setText(String.valueOf(helper.getQuantityFromTable(item)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(ShoppingCartViewHolder holder, CartObject item){
        int position = holder.getAdapterPosition();
        removeByPosition(position);
    }

    public void removeByPosition(int position) {
        DatabaseHelper.getInstance(mContext).deleteItemFromCart(cartList.get(position));
        cartList.remove(position);
        Log.d(KEY, cartList.size() + " this is the size of cart list after an item is removed");
        notifyItemRemoved(position);
    }


    //ok all of these methods need some sort of counter to keep track of what they are adding to or nah


    //method to affect price:
    public double getNewPrice(CartObject item, ShoppingCartViewHolder holder) {
        double newPrice;
        int qty = helper.getQuantityFromTable(item);
        int position = holder.getAdapterPosition();
        if (qty <= 0) {
            removeByPosition(position);
            Log.d(KEY,"quantity hit 0, removed "+item.getmName());
            return 0;
        }
        newPrice = item.getmPrice() * qty;
        newPrice = Math.floor(newPrice * 100) / 100;
            return newPrice;
    }


    //method to affect total:
    public double removeFromPrice(double price) {
        double newPrice = price - price;
        return newPrice;
    }
}

//    //need some sort of counter to keep track
//    public double updateTotal(double price){
//        int newTotal = oldTotal + price;
//        return newTotal;
//    }
//



