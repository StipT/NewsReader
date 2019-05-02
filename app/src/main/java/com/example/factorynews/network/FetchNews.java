package com.example.factorynews.network;


import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class FetchNews {

    private static final String BASE_URL = "https://newsapi.org/v1/";

    private static NewsApi newsApi;

    public static NewsApi getNewsApi() {
        if (newsApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsApi = retrofit.create(NewsApi.class);
            Log.d(TAG, "getNewsApi: Creating new retrofit instance...");
        } else {
            Log.d(TAG, "getNewsApi: Instance already exists");
        }

        return newsApi;
    }
}
