package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by owlslubic on 7/25/16.
 */
public class ShoppingCartRvAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {
    Context mContext;


    //constructor
    public ShoppingCartRvAdapter(Context context) {
        mContext = context;

    }


    //this sets the layout for what each item in the recyclerview should use
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shopping_cart, parent, false);
        ShoppingCartViewHolder shoppingCartViewHolder = new ShoppingCartViewHolder(view);
        return shoppingCartViewHolder;
    }

    //this specifies the contents of each item in the recyclerview
    @Override
    public void onBindViewHolder(final ShoppingCartViewHolder holder, final int position) {
//        if (CartSingleton.getInstance().getCartList().size() == 0) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//            builder.setNeutralButton(R.string.empty_cart_dialog_button, null)
//                    .setTitle(R.string.empty_cart_dialog_title)
//                    .setMessage(R.string.empty_cart_dialog_message);
//            final AlertDialog dialog = builder.create();
//            dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(mContext, "normally this would bring you to MainActivity", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
//        } else {

        //this is where i grab the info from the list in the singleton and set it to the cards in shoppingcart recyclerview

        /**    holder.getAndSetPlantInfoToShoppingCartCardView(CartSingleton.getInstance().getCartList().get(position));**/



            //display the detail dialog when you click on a cart item
//            holder.mCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    DetailDialog detailDialog = new DetailDialog();
//                    detailDialog.launchDetailDialog(view.getContext(), position, CartSingleton.getInstance().getCartList());
//
//                }
//            });
       // }


        holder.mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //need to remove from recyclerview
              //  if(view.equals(holder.mRemove)){
                    removeItemByPosition(holder.getAdapterPosition());
              //  }
                Log.v("cart","removeItemByPosition removed item from recyclerview from sc adapter");
                //deletes the data from db and singleton


                //problem
                CartSingleton.getInstance().getCartList().remove(holder.getAdapterPosition());
                Log.v("cart","removed from singlton via sc adapter");


             //   DatabaseHelper.getInstance(mContext).deleteItemFromCart(CartSingleton.getInstance().getCartList().get(position));
             //   Log.v("cart","method deleteItemFromCart ran from sc adapter");
                Toast.makeText(mContext, "Deleted item from cart!", Toast.LENGTH_SHORT).show();
            }
        });

    }
        @Override
        public int getItemCount () {
            return CartSingleton.getInstance().getCartList().size();
        }


    public void removeItemByPosition(int position){
        CartSingleton.getInstance().getCartList().remove(position);
        notifyItemRemoved(position);
      //  notifyItemRangeChanged(position,mCartItems.size());
    }

    }


