package com.example.factorynews.network;


import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class FetchNews {

    private static final String BASE_URL = "https://newsapi.org/v1/";

    private static NewsApi newsApi;

    public static NewsApi getNewsApi() {
        if (newsApi == null) {
            // int cacheSize = 10 * 1024 * 1024;
            // Cache cache = new Cache(cacheSize);

            // OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //         .cache(cache)
            //         .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //   .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


            newsApi = retrofit.create(NewsApi.class);
            Log.d(TAG, "getNewsApi: Creating new retrofit instance...");
        } else {
            Log.d(TAG, "getNewsApi: Instance already exists");
        }

        return newsApi;
    }


}
