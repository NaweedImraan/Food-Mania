package com.example.naweed.foodmania;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.naweed.foodmania.models.Recipe;
import com.example.naweed.foodmania.utils.FetchRecipes;

import java.util.List;

public class FetchedView extends AppCompatActivity {

    ListView expandableListView;
    List<String> expandableListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetched_view);

        expandableListView = findViewById(R.id.expandableListView);
        expandableListTitle = FetchRecipes.getFetchedRecipes();
        final List<Recipe> recipes = FetchRecipes.getRecipes();
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, expandableListTitle);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //This will make the browser to start on list item clicked
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipes.get(position).getUrl()));
                startActivity(browserIntent);
            }
        });
        expandableListView.setAdapter(a);
    }

}
