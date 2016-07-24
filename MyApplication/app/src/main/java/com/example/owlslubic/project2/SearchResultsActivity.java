package com.example.owlslubic.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchResultsActivity extends AppCompatActivity {

    //query results put in an arraylist
    //simplecursoradapter that inflates a listview of the results
    //onitemclicklisteners will open the detail dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);
    }
}
