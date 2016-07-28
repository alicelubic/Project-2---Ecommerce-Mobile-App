package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    public static final String KEY="key";

    DatabaseHelper helper = DatabaseHelper.getInstance(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ArrayList<CartObject> cartList = helper.getCartItemsAsObjects();
//        if (cartList.size() == 0) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder//.setNeutralButton(R.string.empty_cart_dialog_button, null)
//                    .setTitle(R.string.empty_cart_dialog_title)
//                    .setMessage(R.string.empty_cart_dialog_message);
//            final AlertDialog dialog = builder.create();
//            dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(ShoppingCartActivity.this, "normally this would bring you to MainActivity", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                }
//            });
//            Log.d(KEY,"empty cart dialog launched! or something");
//
//            dialog.show();
//        } else {


            //set up RecyclerView
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_shopping_cart);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(llm);
            ShoppingCartRvAdapter adapter = new ShoppingCartRvAdapter(ShoppingCartActivity.this, cartList);
            recyclerView.setAdapter(adapter);


            //fab to place order
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_shoppingcartactivity);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //this should ultimately be a snackbar with an undo feature
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                    Toast.makeText(ShoppingCartActivity.this, "Your order has been placed!", Toast.LENGTH_SHORT).show();

                    helper.clearCartTableUponCheckout();
                    finish();
                }
            });

        }
    }

