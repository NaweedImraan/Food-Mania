package com.example.naweed.foodmania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //to start the register product activity
    public void registerProductActivity(View view) {
        Intent guessTheCountryIntent = new Intent(this, Register_Product.class);
        startActivity(guessTheCountryIntent);
    }

    //to start the display product activity
    public void displayProductActivity(View view) {
        Intent guessTheCountryIntent = new Intent(this, Display_Product.class);
        startActivity(guessTheCountryIntent);
    }

    public void availabilityActivity(View view) {
        Intent guessTheCountryIntent = new Intent(this, AvailableProducts.class);
        startActivity(guessTheCountryIntent);
    }


    public void editProductActivity(View view) {
        Intent intent = new Intent(this, EditProductList.class);
        startActivity(intent);
    }

    public void searchActivity(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }


    public void recipeActivity(View view) {
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }
}
