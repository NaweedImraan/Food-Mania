package com.example.naweed.foodmania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.example.naweed.foodmania.models.Product;
import com.example.naweed.foodmania.utils.CustomExpandableListAdapter;
import com.example.naweed.foodmania.utils.DatabaseHandler;
import com.example.naweed.foodmania.utils.ExpandableListDataPump;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AvailableProducts extends AppCompatActivity {
    ListView listView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    Map<String, List<String>> expandableListDetail;
    DatabaseHandler db;
    CheckedTextView checkedTextView;
    List<String> availList = new ArrayList<>();
    Button addToKitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_products);
        db = new DatabaseHandler(this);
        List<Product> productList = db.getAllProducts();
        for (Product product : productList) {
            if ("Available".equalsIgnoreCase(product.getAvail())) {
                availList.add(product.getName());
            }
        }
        //Initializing the list on Creating the activity
        listView = findViewById(R.id.expandableListView);
        //getting the data from database and setting
        expandableListDetail = ExpandableListDataPump.getData(db);

        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());


        expandableListAdapter = new CustomExpandableListAdapter(this, availList, "AVAIL");
        listView.setAdapter(expandableListAdapter);


        addToKitchen = findViewById(R.id.addKitchenButton);
    }

    //This method would remove the unchecked from kitchen
    public void removeFromKitchen(View view) {
        System.out.println(expandableListAdapter.getChecked());
        DatabaseHandler db = new DatabaseHandler(this);
        for (String p : expandableListAdapter.getUnchecked()) {
            if (p != null) {
                Product product = db.getProductByName(p);
                product.setAvail("NOT AVAILABLE");
                db.makeNotAvail(product);
            }
        }
        recreate();
    }
}
