package com.example.naweed.foodmania.utils;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class APIConnector {
    private final static String API_KEY = "ef1ef7c74dec5d74df70e1f890f677e0";
    //API key for my account in food2fork

    private static final String BOOK_BASE_URL = "https://www.food2fork.com/api/search?key=" + API_KEY;
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results.
    private static final String PAGE = "page";
    // Parameter to filter by print type.
    private static final String LOG_TAG =
            APIConnector.class.getSimpleName();

//This will return a Json string of recipes
    static String getRecipes(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        System.out.println("API CON 34 = " + queryString);


        try {
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(PAGE, "1")
                    .build();
            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

// Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

// Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            bookJSONString = builder.toString();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, bookJSONString);

        return bookJSONString;

    }
}

