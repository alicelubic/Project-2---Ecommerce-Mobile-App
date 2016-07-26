package com.example.owlslubic.project2;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SearchResultsActivity extends AppCompatActivity {

    //query results get put in objects which are put in an arraylist in the singleton

    //simplecursoradapter that inflates a listview of the results

    //onitemclicklisteners on search result items will open the detail dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);
        //not sure if i will need this
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search_results);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(" ---- ");






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
