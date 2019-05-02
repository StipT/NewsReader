package com.example.factorynews.model.interactor;

import com.example.factorynews.model.data.Article;

import java.util.ArrayList;

public interface Interactor {
    void storeNewsOnRealm(ArrayList<Article> list);

    ArrayList<Article> getNewsFromRealm();

    void fetchNews(final OnFinishedListener onFinishedListener);

    void closeRetrofit();

    interface OnFinishedListener {
        void onFinished(ArrayList<Article> items);
    }
}
