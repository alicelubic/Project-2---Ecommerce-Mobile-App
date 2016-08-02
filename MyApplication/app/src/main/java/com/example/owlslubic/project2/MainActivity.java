package com.example.owlslubic.project2;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String KEY = "key";
    public static final String FIRST_START = "first_start";
    SharedPreferences mSharedPreferences;
    Context context = this;
    public List<Plant> plantArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //this method makes it so that i only insert plant data into the table if this is the first time
//        installChecks();

        DatabaseHelper helper = DatabaseHelper.getInstance(this);
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
        getSupportActionBar().setTitle("");


        //trying to change content on collapsing toolbar, no luck yet
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Invasive Species");


        //this fab takes us to shopping cart activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, ShoppingCartActivity.class));
             //   Toast.makeText(MainActivity.this, "The size "+plantList.size(), Toast.LENGTH_SHORT).show();
            }
        });


        Plant wisteria = new Vine(1, "Chinese Wisteria", "Wisteria sinensis", "This deciduous woody vine is capable of growing to a height of 40 ft. Good luck getting that down!", R.drawable.wisteria, 19.99);
        Plant knotweed = new Weed(2, "Japanese Knotweed", "Reynoutria japonica", "This weed is classified as an invasive species in 39 of the 50 United States, so why not add yours to the list!", R.drawable.knotweed, 24.99);
        Plant ivy = new Vine(3, "English Ivy", "Hedera helix", "English Ivy is a rampant, clinging evergreen vine that has been known to crowd out and choke other plants, creating an \"ivy desert\"", R.drawable.ivy, 12.99);
        Plant ailanthus = new Tree(4, "Tree of Heaven", "Ailanthus altissima", "This tree resprouts vigorously when cut, so your neighbors' efforts to suppress this beast will drive them crazy for decades to come!", R.drawable.ailanthus, 99.99);
        Plant garlicMustard = new Weed(5, "Garlic Mustard", "Alliaria petiolata", "This stinky weed cannot be killed.", R.drawable.garlicmustard, 10.98);
        Plant daylily = new Angiosperm(6, "Orange Daylily", "Hemerocallis fulva", "Its beautiful bright orange blossoms will dazzle your neighbors, who will never suspect that these flowering plants are horrible weeds.", R.drawable.daylily, 21.99);
        Plant kudzu = new Vine(7, "Kudzu", "Pueraria lobata", "A lovely vine that will take over and deprive all other plants of resources. Word.", R.drawable.kudzu, 110.99);
        Plant paulownia = new Tree(8, "Princess Tree", "Paulownia tomentosa", "Princess Tree is an showy and aggressive ornamental tree, so it should get along great with your neighbors!", R.drawable.paulownia, 99.01);
        Plant bittersweet = new Vine(9, "Oriental Bittersweet", "Celastrus orbiculatus", "A vine that grows aggressively, smothering trees, shrubs, and other irritating entities like your neighbors. Just kidding.", R.drawable.oriental_bittersweet, 82.99);
        Plant bamboo = new Grass(10, "Bamboo", "Bambusoidae", "This giant grass is a giant pain in the ass. Once established, it is impossible to control, for each sprout can grow 12 inches a day. That'll teach 'em.", R.drawable.bamboo, 20.20);

InsertPlantDataAsyncTask insertPlantDataAsyncTask = new InsertPlantDataAsyncTask();
        insertPlantDataAsyncTask.execute(wisteria, knotweed, ivy, ailanthus,garlicMustard,daylily,kudzu,paulownia,bittersweet,bamboo);


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

            DatabaseHelper.getInstance(this).insertPlantData();
            int x = mSharedPreferences.getInt(FIRST_START, 0) + 1;
        }
    }

Plant[] plantArray = new Plant[10];

    //asyncTask to insert the plant data into the database -- currently this is two separate methods in the db Helper
private class InsertPlantDataAsyncTask extends AsyncTask<Plant,Void,Void>{

        @Override
        protected Void doInBackground(Plant... plants) {
            DatabaseHelper helper = DatabaseHelper.getInstance(getApplicationContext());

            helper.insertPlantTableRow(plants[0]);
            helper.insertPlantTableRow(plants[1]);
            helper.insertPlantTableRow(plants[2]);
            helper.insertPlantTableRow(plants[3]);
            helper.insertPlantTableRow(plants[4]);
            helper.insertPlantTableRow(plants[5]);
            helper.insertPlantTableRow(plants[6]);
            helper.insertPlantTableRow(plants[7]);
            helper.insertPlantTableRow(plants[8]);
            helper.insertPlantTableRow(plants[9]);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "plant data inserted!", Toast.LENGTH_SHORT).show();

        }
    }


//    private class SearchPlantsAsyncTask extends AsyncTask<String, Void, Cursor>{
//
//        @Override
//        protected Cursor doInBackground(String query) {
//
//
//
//
//            return DatabaseHelper.getInstance(getApplicationContext()).searchPlants(query);
//        }
//    }


}


