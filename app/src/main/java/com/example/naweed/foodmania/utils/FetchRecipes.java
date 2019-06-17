package com.example.naweed.foodmania.utils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.naweed.foodmania.FetchedView;
import com.example.naweed.foodmania.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class FetchRecipes extends AsyncTask<String, String, String> {

    static List<String> recipeListTitle;
    static List<Recipe> recipes;
    ListView listView;
    String recipeList;
    Context context;


   public FetchRecipes(String recipeList, ListView listView, Context context) {
        this.listView = listView;
        this.recipeList = recipeList;
        this.context = context.getApplicationContext();

    }

    public static List<Recipe> getRecipes() {
        return recipes;
    }

    public static List<String> getFetchedRecipes() {
        //recipeListTitle.clear();
        return recipeListTitle;
    }

    protected String doInBackground(String... params) {

        return APIConnector.getRecipes(params[0]);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("recipes");
            int i = 1;
            String title = null;
            String authors = null;
            recipeListTitle = new ArrayList<>();
            recipes = new ArrayList<>();
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get the current item information.
                JSONObject recipeJson = itemsArray.getJSONObject(i);

                String name = recipeJson.get("title").toString();
                String url = recipeJson.get("source_url").toString();
                String img = recipeJson.get("image_url").toString();
                if (name != null) {
                    System.out.println(url);
                    Recipe recipe = new Recipe(name, url, img);
                    recipes.add(recipe);
                    recipeListTitle.add(name);

                } else {

                    System.out.println("NO RESULtS");
                }
                //  System.out.println("-->"+name);
                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    // title = name.getString("title");

                } catch (Exception e) {
                    //TODO : Handle no results from API
                    // If onPostExecute does not receive a proper JSON string,
                    // update the UI to show failed results.

                    e.printStackTrace();
                }

                // Move to the next item.
                i++;

            }

            context.startActivity(new Intent(context, FetchedView.class));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
