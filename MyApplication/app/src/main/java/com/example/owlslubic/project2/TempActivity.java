//package com.example.owlslubic.project2;
//
//import android.provider.ContactsContract;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class TempActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_temp);
//
//        ListView listView = (ListView) findViewById(R.id.temp);
//        TextView textview = (TextView) findViewById(android.R.id.text1);
//        DatabaseHelper helper = DatabaseHelper.getInstance(this);
//        ArrayList<TempCartObject> list = (ArrayList<TempCartObject>) helper.checkCartTableData();
//        Log.d("cart", "it ran the check cart table method");
//
//
//    listView.setAdapter(new ArrayAdapter<TempCartObject>(this,android.R.layout.simple_list_item_1, list));
//
//
//    }
//}
