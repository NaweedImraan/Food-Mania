package com.example.naweed.foodmania;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.naweed.foodmania.utils.CustomExpandableListAdapter;
import com.example.naweed.foodmania.utils.DatabaseHandler;
import com.example.naweed.foodmania.utils.ExpandableListDataPump;
import com.example.naweed.foodmania.utils.FetchRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeActivity extends AppCompatActivity {
    private final static String API_KEY = "ef1ef7c74dec5d74df70e1f890f677e0";
    //API KEY --- ef1ef7c74dec5d74df70e1f890f677e0
    ListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    Map<String, List<String>> expandableListDetail;
    DatabaseHandler db;
    CheckedTextView checkedTextView;
    Button addToKitchen;
    String recipeString = "";
    String apiCallString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        db = new DatabaseHandler(this);
        expandableListView = findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData(db);
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, "display");
        expandableListView.setAdapter(expandableListAdapter);
        addToKitchen = findViewById(R.id.addKitchenButton);

    }


    public void findRecipe(View view) {

        System.out.println(expandableListAdapter.getChecked());
        List<String> recipes = expandableListAdapter.getChecked();
        recipeString = "";
        //ading the selected recipes to a string to use it in the api call
        for (String product : recipes) {
            recipeString = recipeString.concat(product + ",");
        }

//setting the api url
        apiCallString = "https://www.food2fork.com/api/search?key=" + API_KEY + "&q=" + recipeString + "page=1";

        String queryString = recipeString;

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {

            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);

            new FetchRecipes(queryString, expandableListView, this.getApplicationContext()).execute(queryString);

        } else {
            if (queryString.length() == 0) {

            } else {
                Toast.makeText(this, "No Network Connection!",
                        Toast.LENGTH_LONG).show();
            }
        }
        recipeString = "";

    }


}
