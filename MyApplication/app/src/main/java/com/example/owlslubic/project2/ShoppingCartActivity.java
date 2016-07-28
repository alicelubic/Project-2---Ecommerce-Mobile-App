package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    final DatabaseHelper helper = DatabaseHelper.getInstance(this);
    //CartSingleton cart = CartSingleton.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        //this will give me a list of items i can manipulate
   //   List<Plant> cartItems = CartSingleton.getInstance().getCartList();

        //NEED TO FIND ALTERNATIVE WAY TO GET DATA FROM SHOPPING CART TABLE TO DISPLAY HERE


     //   ArrayList<String> arrayList = helper.getCartData();
        ArrayList<TempCartObject> cartList = helper.getCartItemsAsObjects();


        //set up RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_shopping_cart);
        LinearLayoutManager llm= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
          ShoppingCartRvAdapter adapter = new ShoppingCartRvAdapter(ShoppingCartActivity.this, cartList);
          recyclerView.setAdapter(adapter);
       // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        //fab to place order
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_shoppingcartactivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this should ultimately be a snackbar with an undo feature
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Toast.makeText(ShoppingCartActivity.this, "Your order has been placed!", Toast.LENGTH_SHORT).show();

                //this will, as the name suggests, clear the info from the cart table
                helper.clearCartTableUponCheckout();
      //          CartSingleton.getInstance().emptyCart();
                //must I then also clear the carviews from the recyclerview?
                //will finish achieve that?
              finish();
            }
        });


        //some sort of IF SHOPPINGCARTLIST.SIZE()==0, launch dialog -- need to make some sort of shoppingcartlist also

        //testing this out in the adapter
//        AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
//        builder.setNeutralButton(R.string.empty_cart_dialog_button,null)
//                .setTitle(R.string.empty_cart_dialog_title)
//                .setMessage(R.string.empty_cart_dialog_message);
//        final AlertDialog dialog = builder.create();
//        dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ShoppingCartActivity.this, MainActivity.class)); //would it be better to do finish() for this?
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
    }
//    public void goHome() {
//        startActivity(new Intent(this, MainActivity.class)); //would it be better to do finish() for this?
//    }

//
//    //menu stuff   -- not sure if i want/need a menu here
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
////        return true; i think the reason i used super here is because then it is the same menu as before.... maybe
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.home:
//                onNavigateUp();
//                return true;
//            case R.id.search:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
