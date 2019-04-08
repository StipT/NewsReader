package com.example.factorynews.network;

import com.example.factorynews.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    @GET("articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398")
    Call<News> getNews();
}
