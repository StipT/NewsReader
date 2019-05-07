package com.example.factorynews.model.interactor;


import android.util.Log;

import com.example.factorynews.model.data.Article;
import com.example.factorynews.model.data.News;
import com.example.factorynews.network.FetchNews;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;


public class InteractorImpl implements Interactor {

    private static final String TAG = "InteractorImpl";

    private Realm realm;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;


    public InteractorImpl() {
        realm = Realm.getDefaultInstance();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void storeNewsOnRealm(List<Article> list) {
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
    public void fetchNews(OnFinishedListener onFinishedListener) {
        Single<Response<News>> singleNews = FetchNews.getNewsApi().getNews();

        disposable =
                singleNews.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(response -> {
                            News news = response.body();
                            List<Article> articleList = news.getArticles();
                            storeNewsOnRealm(articleList);
                            onFinishedListener.onFinished(articleList);
                            return articleList;
                        })
                        .subscribe(success -> Log.d(TAG, "fetchNews: Successful subscription"),
                                error -> error.printStackTrace());

        compositeDisposable.add(disposable);
    }

    @Override
    public void disposeDisposable() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
