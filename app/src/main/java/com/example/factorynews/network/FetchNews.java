package com.example.factorynews.network;


import android.util.Log;

import com.example.factorynews.model.Article;
import com.example.factorynews.model.News;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class FetchNews{

    private static final String BASE_URL = "https://newsapi.org/v1/";

    private RealmList<Article> newsList = new RealmList<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private NewsApi newsApi = retrofit.create(NewsApi.class);
    private Call<News> call = newsApi.getNews();

    public interface RetrofitListener {
        void onCompletion(RealmList<Article> list);
    }

    public void enqueueNews(final RetrofitListener retrofitListener) {
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Response code: " + response.code());
                } else {
                    try {
                        News news = response.body();

                        Log.d(TAG, "onResponse: News status " + news.getStatus());
                        Log.d(TAG, "onResponse: Source " + news.getSource());
                        for (Article r : news.getArticles()) {
                            newsList.add(r);
                            Log.d(TAG, "onResponse: ==> " + r.toString());
                        }
                    }catch(NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                retrofitListener.onCompletion(newsList);
            }


            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d(TAG, "onFailure: Enqueue failed");
                t.printStackTrace();
            }
        });
    }
}
