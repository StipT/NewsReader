package com.example.factorynews.model.interactor;

import com.example.factorynews.model.data.Article;

import java.util.ArrayList;
import java.util.List;

public interface Interactor {
    void storeNewsOnRealm(List<Article> list);

    ArrayList<Article> getNewsFromRealm();

    void fetchNews(OnFinishedListener onFinishedListener);

    void disposeDisposable();

    interface OnFinishedListener {
        void onFinished(List<Article> items);
    }
}
