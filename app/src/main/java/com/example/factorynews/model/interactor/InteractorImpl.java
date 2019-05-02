package com.example.factorynews.model.interactor;

import android.util.Log;

import com.example.factorynews.model.data.Article;
import com.example.factorynews.model.data.News;
import com.example.factorynews.network.FetchNews;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class InteractorImpl implements Interactor {

    private Realm realm;
    private Call<News> call;


    public InteractorImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void storeNewsOnRealm(ArrayList<Article> list) {
        realm.beginTransaction();
        realm.deleteAll();
        realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
    }

    @Override
    public ArrayList<Article> getNewsFromRealm() {
        realm.beginTransaction();
        RealmResults<Article> query = realm.where(Article.class).findAll();
        ArrayList<Article> articleList = new ArrayList<>(realm.copyFromRealm(query));
        realm.commitTransaction();
        return articleList;
    }

    @Override
    public void fetchNews(final OnFinishedListener onFinishedListener) {

        call = FetchNews.getNewsApi().getNews();
        final ArrayList<Article> newsList = new ArrayList<>();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Response code: " + response.code());
                } else {
                    try {
                        News news = response.body();
                        newsList.addAll(news.getArticles());

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                onFinishedListener.onFinished(newsList);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d(TAG, "onFailure: Enqueue failed");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void closeRetrofit() {
        if (!call.isCanceled()) {
            call.cancel();
        }
    }
}
