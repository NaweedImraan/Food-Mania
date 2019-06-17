package com.example.naweed.foodmania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.naweed.foodmania.models.Product;
import com.example.naweed.foodmania.utils.DatabaseHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductDetailActivity extends AppCompatActivity {


    EditText name;
    EditText weight;
    EditText price;
    EditText desc;
    DatabaseHandler db;
    Spinner availSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        price = findViewById(R.id.price);
        desc = findViewById(R.id.desc);
        availSpinner = findViewById(R.id.availSpinner);
        List<String> categories = new ArrayList<String>();
        categories.add("AVAILABLE");
        categories.add("NOT AVAILABLE");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        availSpinner.setAdapter(dataAdapter);


        String productName = getIntent().getStringExtra("product");
        db = new DatabaseHandler(this);
        Product p = db.getProductByName(productName);
        name.setText(p.getName());
        weight.setText(String.valueOf(p.getWeight()));
        price.setText(String.valueOf(p.getPrice()));
        desc.setText(p.getDescription());
        String avail = p.getAvail().equalsIgnoreCase("AVAILABLE") ? "AVAILABLE" : "NOT AVAILABLE";
        availSpinner.setSelection(dataAdapter.getPosition(avail));


    }

    //updating the database on click
    public void updateDb(View view) {
        String productName = getIntent().getStringExtra("product");
        Product p = db.getProductByName(productName);
        p.setName(name.getText().toString());
        p.setAvail(availSpinner.getSelectedItem().toString());
        p.setDescription(desc.getText().toString());
        p.setPrice(new BigDecimal(price.getText().toString()));
        p.setWeight(Double.parseDouble(weight.getText().toString()));
        db.updateProduct(p);
        finish();
    }

}
