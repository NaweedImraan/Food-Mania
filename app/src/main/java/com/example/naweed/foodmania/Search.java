package com.example.naweed.foodmania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.naweed.foodmania.models.Product;
import com.example.naweed.foodmania.utils.CustomExpandableListAdapter;
import com.example.naweed.foodmania.utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    EditText searchText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchText = findViewById(R.id.searchText);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search();
            }
        });


    }

    public void search(View view) {
        final DatabaseHandler db = new DatabaseHandler(this);
        CustomExpandableListAdapter listAdapter;
        List<String> titles = new ArrayList<>();
        List<Product> productList = db.search(searchText.getText().toString());

        for (Product p : productList) {
            titles.add(p.getName());
        }

        listView = findViewById(R.id.expandableListView);
        listAdapter = new CustomExpandableListAdapter(this, titles, "editProduct");
        listView.setAdapter(listAdapter);

    }

    public void search() {
        final DatabaseHandler db = new DatabaseHandler(this);
        CustomExpandableListAdapter listAdapter;
        List<String> titles = new ArrayList<>();
        List<Product> productList = db.search(searchText.getText().toString());

        for (Product p : productList) {
            titles.add(p.getName());
        }

        listView = findViewById(R.id.expandableListView);
        listAdapter = new CustomExpandableListAdapter(this, titles, "editProduct");
        listView.setAdapter(listAdapter);

    }


}
