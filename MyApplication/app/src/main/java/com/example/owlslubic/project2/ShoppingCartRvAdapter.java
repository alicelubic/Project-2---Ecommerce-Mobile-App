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
import java.util.Collections;
import java.util.List;

/**
 * Created by owlslubic on 7/25/16.
 */

public class ShoppingCartRvAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> implements ItemTouchHelperAdapter{
    public static final String KEY = "key";
    Context mContext;
    List<CartObject> cartList = new ArrayList<>();
    DatabaseHelper helper = DatabaseHelper.getInstance(mContext);
    double total;



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
        holder.mPrice.setText("$" + item.getmPrice());
        holder.mQuantity.setText(String.valueOf(item.getmQuantity()));
        holder.mPlantImage.setImageResource(item.getmImage());

        //  Log.d(KEY, cartList.size()+ "this is the size of the cartlist upon being bound to the shopping cart");

        total += helper.getQuantityFromTable(item) * item.getmPrice();
        holder.mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this should remove from recyclerview and table
                removeByPosition(holder.getAdapterPosition());

                //   Log.d(KEY, "removeItemByPosition removed item from recyclerview from sc adapter");
                Toast.makeText(mContext, "Adios, " + item.getmName() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //  total += helper.getQuantityFromTable(item) * item.getmPrice();
                helper.increaseQty(item);
                holder.mQuantity.setText(String.valueOf(String.valueOf(helper.getQuantityFromTable(item))));
                holder.mPrice.setText("$" + getNewPrice(item, holder));
//                holder.mTotal.setText("$"+total);

            }
        });
        holder.mDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helper.decreaseQty(item);
                holder.mQuantity.setText(String.valueOf(helper.getQuantityFromTable(item)));
                holder.mPrice.setText("$" + getNewPrice(item, holder));
//                holder.mTotal.setText("$"+total);
         //       total = total + holder.mPrice.getText();

            }
        });

    }

    //method that takes whatever is in the setTexts and adds them...

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


    //method to affect price:
    public double getNewPrice(CartObject item, ShoppingCartViewHolder holder) {
        double price = item.getmPrice();
        double newPrice;
        int qty = helper.getQuantityFromTable(item);
        int position = holder.getAdapterPosition();
        if (qty > 0) {
            if (qty == 1) {
                return price;
            }
            newPrice = price*qty;
            newPrice = Math.floor(newPrice * 100) / 100;
         //   total += helper.getQuantityFromTable(item) * item.getmPrice();
            return newPrice;

        } else {
            removeByPosition(position);
            Toast.makeText(mContext, "Adios, " + item.getmName() + "!", Toast.LENGTH_SHORT).show();
            Log.d(KEY, "quantity hit 0, removed " + item.getmName());
         //   total += helper.getQuantityFromTable(item) * item.getmPrice();
            return 0;
        }

    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition){
            for(int i = fromPosition;i<toPosition;i++){
                Collections.swap(cartList, i, i+1);
            }
        }else{
            for (int i = fromPosition; i > toPosition; i--){
                Collections.swap(cartList, i, i-1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        removeByPosition(position);

    }
}