package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.store_name);

        //trying to change content on collapsing toolbar
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("");


        //this fab takes us to shopping cart activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, ShoppingCartActivity.class));

            }
        });



        //currently the dialog is set to this temp fab because i don't have the cardviews up yet
        FloatingActionButton fabTemp = (FloatingActionButton) findViewById(R.id.fab_temp);
        fabTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //build and set custom alert dialog for the item detail page
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View dialogLayout = inflater.inflate(R.layout.dialog_product_detail, null);
                builder.setView(dialogLayout);
                final AlertDialog dialog = builder.create();
                dialog.show();

                ImageButton button = (ImageButton) dialogLayout.findViewById(R.id.button_cancel_dialog);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                FloatingActionButton addToCart = (FloatingActionButton) dialogLayout.findViewById(R.id.fab_detail_dialog);
                addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Added to cart!", Toast.LENGTH_SHORT).show();

                        //add item to shoppingcart singleton list

                        dialog.dismiss();
                    }
                });
            }
        });
    }


    //menu stuff

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
        //in the demo we used return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            Toast.makeText(MainActivity.this, "Searchin'!", Toast.LENGTH_SHORT).show();
            return true;
            //hitting enter after typing in a search term should start up the searchresults activity  where the listview will display the query results

            //startActivity(new Intent(MainActivity.this, SearchResultsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
