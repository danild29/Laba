package com.example.laba.code.interfaces;

import android.util.Log;

import com.example.laba.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class FetchBooksTask {
    private static final String TAG = "FetchBooksTask";
    private static final String JSON_URL = "https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/Books2022.json";

    public Book[] GetBookInfo() {
        JSONArray jsonArray = null;
        try {
            URL url = new URL(JSON_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder jsonStringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    jsonStringBuilder.append(line);
                }

                // Парсим JSON
                jsonArray = new JSONArray(jsonStringBuilder.toString());
                return ParseJson(jsonArray);
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error fetching data", e);
        }

        return new Book[0];
    }

    private Book[] ParseJson(JSONArray jsonArray) {
        if (jsonArray != null) {
            try {

//                {
//                    "Author": "Лев Толстой",
//                        "Genre": "Роман, Классика",
//                        "Name": "Анна Каренина",
//                        "PublicationDate": "1878",
//                        "rating": 8
//                },

                Book[] books = new Book[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject bookJson = jsonArray.getJSONObject(i);
                    Book book = new Book();
                    book.author = bookJson.getString("Author");
                    book.genre = bookJson.getString("Genre");
                    book.name = bookJson.getString("Name");
                    book.publicationDate = bookJson.getString("PublicationDate");
                    book.rating = bookJson.getInt("rating");
                    books[i] = book;
                    // Выводим информацию в лог
                    Log.d(TAG, "Book " + (i + 1) + ":");
                    Log.d(TAG, "author: " + book.author);
                    Log.d(TAG, "genre: " + book.genre);
                    Log.d(TAG, "name: " + book.name);
                    Log.d(TAG, "publicationDate: " + book.publicationDate);
                    Log.d(TAG, "rating: " + book.rating);
                }
                return books;
            } catch (Exception e) {
                Log.e(TAG, "Error parsing JSON", e);
            }
        } else {
            Log.e(TAG, "No data received from API");
        }


        return new Book[0];
    }
}
