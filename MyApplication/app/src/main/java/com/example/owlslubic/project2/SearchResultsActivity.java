package com.example.owlslubic.project2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {
    CursorAdapter mCursorAdapter;
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);

        mListView = (ListView) findViewById(R.id.listview_search_results);
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            Cursor cursor = DatabaseHelper.getInstance(this).searchPlants(query);
            mCursorAdapter = new CursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup parent) {
                    return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
                }

                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    int index = cursor.getColumnIndex(DatabaseHelper.COL_COMMON_NAME);
                    textView.setText(cursor.getString(index));

                }
            };
            mListView.setAdapter(mCursorAdapter);
        }

        //launches a dialog for the wrong plant
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DetailDialog dialog = new DetailDialog();
                List<Plant> plantList = DatabaseHelper.getInstance(SearchResultsActivity.this).getListOfAllPlants();
                dialog.launchDetailDialog(view.getContext(), position, plantList);
//pass in the name from the list into teh new launch method,

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_searchresultactivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchResultsActivity.this, ShoppingCartActivity.class));
            }
        });


//        i want to be able to search by COMMON NAME, LATIN NAME, or PLANT TYPE
    }

    //figure out if this already has a toolbar with the searchview, and if not, inflate the menu here


}
