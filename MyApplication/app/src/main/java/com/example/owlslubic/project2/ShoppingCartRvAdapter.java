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
        Log.v("cart", "constructor for rvadapter");

    }

    //this sets the layout for what each item in the recyclerview should use
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shopping_cart, parent, false);
        ShoppingCartViewHolder viewHolder = new ShoppingCartViewHolder(view);

        Log.v("cart", "onCreateViewHolder inflated the cardview");
        return viewHolder;
    }


    //this specifies the contents of each item in the recyclerview
    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, final int position) {
        if (cartList.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setNeutralButton(R.string.empty_cart_dialog_button, null)
                    .setTitle(R.string.empty_cart_dialog_title)
                    .setMessage(R.string.empty_cart_dialog_message);
            final AlertDialog dialog = builder.create();
            dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "normally this would bring you to MainActivity", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            Log.d(KEY,"empty cart dialog launched! or something");

            dialog.show();
        } else {
            CartObject item = cartList.get(position);
            holder.mName.setText(item.getmName());
            holder.mPrice.setText(String.valueOf(item.getmPrice()));
            holder.mQuantity.setText(String.valueOf(item.getmQuantity()));
            holder.mPlantImage.setImageResource(item.getmImage());

            Log.d(KEY, "onbindviewholder in rv adapter did the binding... of the views");
        }


        holder.mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this should remove from recyclerview and table
                removeByPosition(holder.getAdapterPosition());

                Log.d(KEY, "removeItemByPosition removed item from recyclerview from sc adapter");

                Toast.makeText(mContext, "Deleted item from cart!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public void removeByPosition(int position) {
       cartList.remove(position);
        notifyItemRemoved(position);

     //   DatabaseHelper.getInstance(mContext).deleteItemFromCart(cartList.get(position));


    }

}


