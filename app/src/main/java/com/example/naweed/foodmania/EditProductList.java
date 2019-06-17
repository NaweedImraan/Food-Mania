package com.example.naweed.foodmania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.naweed.foodmania.utils.CustomExpandableListAdapter;
import com.example.naweed.foodmania.utils.DatabaseHandler;
import com.example.naweed.foodmania.utils.ExpandableListDataPump;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditProductList extends AppCompatActivity {
    ListView listView;
    CustomExpandableListAdapter listAdapter;
    List<String> productList;
    Map<String, List<String>> expandableListDetail;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_list);
        db = new DatabaseHandler(this);
        listView = findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData(db);
        productList = new ArrayList<>(expandableListDetail.keySet());
        listAdapter = new CustomExpandableListAdapter(this, productList, "editProduct");
        listView.setAdapter(listAdapter);


    }

    //Recreating the view on restart
    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
