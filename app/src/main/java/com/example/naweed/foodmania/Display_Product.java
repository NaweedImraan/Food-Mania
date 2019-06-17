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

public class Display_Product extends AppCompatActivity {
    ListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    Map<String, List<String>> expandableListDetail;
    DatabaseHandler db;
    CheckedTextView checkedTextView;
    Button addToKitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__product);
        db = new DatabaseHandler(this);
        expandableListView = findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData(db);
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, "display");
        expandableListView.setAdapter(expandableListAdapter);
        addToKitchen = findViewById(R.id.addKitchenButton);
    }

    //This will add the selected to kitchen on button click
    public void addToKitchen(View view) {
        System.out.println(expandableListAdapter.getChecked());
        DatabaseHandler db = new DatabaseHandler(this);
        for (String p : expandableListAdapter.getChecked()) {
            if (p != null) {
                Product product = db.getProductByName(p);

                product.setAvail("AVAILABLE");
                System.out.println(product);
                db.makeAvail(product);
                System.out.println("AFTER AVAiL = " + product);
            }
        }
    }
}
