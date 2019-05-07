package com.example.factorynews.network;

import com.example.factorynews.model.data.News;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsApi {
    @Headers("Cache-Control: max-age=300")
    @GET("articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398")
    Single<Response<News>> getNews();
}
