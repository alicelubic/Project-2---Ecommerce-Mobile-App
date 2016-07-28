package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String KEY = "key";
    public static final String FIRST_START = "first_start";
    SharedPreferences mSharedPreferences;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this method makes it so that i only insert plant data into the table if this is the first time
        installChecks();
        DatabaseHelper helper = DatabaseHelper.getInstance(this);

        // helper.clearPlantTableData();

        //this method is where the items are instantiated and added to the list
        final List<Plant> plantList = helper.getListOfAllPlants();


        //set up RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_main_store);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        MainRvAdapter adapter = new MainRvAdapter(MainActivity.this, plantList); //this refers to the exact list created in dbhelper
        recyclerView.setAdapter(adapter);


        //set up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.store_name);

        //trying to change content on collapsing toolbar, no luck yet
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
                Toast.makeText(MainActivity.this, "The size "+plantList.size(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    //menu stuff

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        //associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, SearchResultsActivity.class);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        searchView.setSearchableInfo(searchableInfo);
        searchView.setQueryHint("Whatcha looking for?");
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void installChecks() {
        mSharedPreferences = getSharedPreferences(FIRST_START, 0);
        if (mSharedPreferences.getInt(FIRST_START, 0) < 1 == true) {
           // Log.d(KEY,p)
            DatabaseHelper.getInstance(this).insertPlantData();
            int x = mSharedPreferences.getInt(FIRST_START, 0) + 1;
        }
    }

}


