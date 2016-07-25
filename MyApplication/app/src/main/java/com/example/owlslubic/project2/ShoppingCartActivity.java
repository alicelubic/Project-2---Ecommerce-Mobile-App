package com.example.owlslubic.project2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ShoppingCartActivity extends AppCompatActivity {
    //this activity will be populated by what is in the singleton
    //it will inflate the recyclerview


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //set up RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_shopping_cart);
        //tutorial suggests i use recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        //  ShoppingCartRvAdapter adapter = new ShoppingCartRvAdapter(THIS WILL HOLD THE VARIABLE FOR THE LIST OF PLANT ITEMS or maybe the singleton idk);
        //  recyclerView.setAdapter(adapter);


        //had an error when I declared a toolbar here, but i don't think i need it anyway?
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cart);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Your Cart");



        //fab to place order
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_shoppingcartactivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this should ultimately be a snackbar with an undo feature
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Toast.makeText(ShoppingCartActivity.this, "Your order has been placed!", Toast.LENGTH_SHORT).show();

                finish();
                /** will having finish() here clear the listview? it should either way **/


            }
        });

    }



    //menu stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
//        return true; i think the reason i used super here is because then it is the same menu as before.... maybe
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                onNavigateUp();
                return true;
            case R.id.search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
