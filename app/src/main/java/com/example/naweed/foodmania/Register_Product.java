package com.example.naweed.foodmania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.naweed.foodmania.models.Product;
import com.example.naweed.foodmania.utils.DatabaseHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Register_Product extends AppCompatActivity {

    DatabaseHandler mydb;
    EditText nameField;
    EditText descField;
    EditText priceField;
    EditText weightField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__product);
        mydb = new DatabaseHandler(this);
        nameField = findViewById(R.id.productNameText);
        descField = findViewById(R.id.descText);
        priceField = findViewById(R.id.priceText);
        weightField = findViewById(R.id.weightText);

    }


    public void addProduct(View view) {

        Product p = new Product(new BigDecimal(priceField.getText().toString()), Double.parseDouble(weightField.getText().toString()), descField.getText().toString(), nameField.getText().toString());
        p.setAvail("NOT AVAILABLE");
        System.out.println(p);
        mydb.addProduct(p);
        List<Product> list = mydb.getAllProducts();
        list = new ArrayList<>(list);
        System.out.println(list);
    }
}
