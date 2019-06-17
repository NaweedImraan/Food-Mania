package com.example.naweed.foodmania.utils;

import com.example.naweed.foodmania.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ExpandableListDataPump {
    public static Map<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> recipeDetails = new ArrayList<String>();
        return expandableListDetail;
    }

    public static Map<String, List<String>> getData(DatabaseHandler db) {
        Map<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<Product> allProducts = db.getAllProducts();

        for (Product p :
                allProducts) {
            List<String> details = new ArrayList<>();
            details.add("Price :" +p.getPrice().toString());
            expandableListDetail.put(p.getName().toUpperCase(),details);
        }
        expandableListDetail = new TreeMap<>(expandableListDetail);
        return expandableListDetail;
    }
}
