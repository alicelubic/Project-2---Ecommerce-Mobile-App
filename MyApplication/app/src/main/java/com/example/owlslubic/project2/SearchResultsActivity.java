package com.example.owlslubic.project2;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SearchResultsActivity extends AppCompatActivity {


    //simplecursoradapter that inflates a listview of the results, or add the results to a string array and use an arrayadapter to display them

    //onitemclicklisteners on search result items will open the detail dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);
        //not sure if i will need this
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search_results);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(" ---- ");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_searchresultactivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchResultsActivity.this, ShoppingCartActivity.class));
            }
        });



        //FOR SEARCHABILITY:
        //we will make a query that returns a cursor in the DBhelper, and then here, we tell this activity to handle our search:
//        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
//            String query = getIntent().getStringExtra(SearchManager.QUERY);
//            Cursor cursor = DatabaseHelper.getInstance(this).searchPlants(query); --ps you still hvae to make a method that does this and returns a cursor
//                    then we set this query to the listview
//        }
    }

    //figure out if this already has a toolbar with the searchview, and if not, inflate the menu here




}
