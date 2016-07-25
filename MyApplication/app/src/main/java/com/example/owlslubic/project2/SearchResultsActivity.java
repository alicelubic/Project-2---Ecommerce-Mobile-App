package com.example.owlslubic.project2;

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
    }
}
