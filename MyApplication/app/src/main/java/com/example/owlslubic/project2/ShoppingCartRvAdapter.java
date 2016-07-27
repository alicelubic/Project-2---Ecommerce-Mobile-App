package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by owlslubic on 7/25/16.
 */
public class ShoppingCartRvAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {
    CartSingleton cart = CartSingleton.getInstance();
    List<Plant> mCartItems= cart.getCartList();
    Context mContext;
    DatabaseHelper helper = DatabaseHelper.getInstance(mContext);



    //constructor
    public ShoppingCartRvAdapter(Context context, List<Plant> cartItems) {
        mContext = context;
        mCartItems = cartItems;
    }


    //here I will declare the object list to be displayed, and I will set the adapter to that list
    //something like List<Plant> plants;
    //ShoppingCartRvAdapter(List<Plant> plants){this.plants = plants;


    //this sets the layout for what each item in the recyclerview should use
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shopping_cart, parent, false);
        ShoppingCartViewHolder shoppingCartViewHolder = new ShoppingCartViewHolder(view);
        return shoppingCartViewHolder;
    }

    //this specifies the contents of each item in the recyclerview
    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, final int position) {
        //this is where i will include a collection of my plants -- i will call a method from the dbHelper that returns a list of Plant objects
//        if (mCartItems.size() == 0) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//            builder.setNeutralButton(R.string.empty_cart_dialog_button, null)
//                    .setTitle(R.string.empty_cart_dialog_title)
//                    .setMessage(R.string.empty_cart_dialog_message);
//            final AlertDialog dialog = builder.create();
//            dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //     startActivity(new Intent(mContext, MainActivity.class)); //would it be better to do finish() for this?
//                    Toast.makeText(mContext, "normally this would bring you to MainActivity", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
//        } else {
            holder.getAndSetPlantInfoToShoppingCartCardView(mCartItems.get(position));
        //this should display the detail dialog when you click on a cart item
            //this part works
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailDialog detailDialog = new DetailDialog();
                detailDialog.launchDetailDialog(view.getContext(), position, mCartItems);

            }
        });
    }



    @Override
    public int getItemCount() {
        return mCartItems.size();
    }


}
