package com.example.factorynews.news_list_screen.presenter;


import android.util.Log;

import com.example.factorynews.model.interactor.InteractorImpl;
import com.example.factorynews.news_list_screen.view.NewsListView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;

import static android.support.constraint.Constraints.TAG;

public class NewsListPresenterImpl implements NewsListPresenter {

    private NewsListView newsListView;
    private InteractorImpl interactorImpl;


    public NewsListPresenterImpl(NewsListView newsListView) {
        this.newsListView = newsListView;
        interactorImpl = new InteractorImpl();
    }

    @Override
    public void onCreate() {
        if (newsListView != null) {
            newsListView.showProgressBar();
            newsListView.bindRecyclerView();
            interactorImpl.fetchNews(items -> newsListView.updateItems(items));
            newsListView.hideProgressBar();

        }
    }

    public void getUpToDateNews() {

        ResourceObserver<Long> timeObserver = new ResourceObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                interactorImpl.fetchNews(items -> newsListView.updateItems(items));
                Log.d(TAG, "onNext: News are now up to date");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Task completed");
            }
        };

        Disposable disposable = Observable
                .interval(30, TimeUnit.MINUTES)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(timeObserver);
    }



    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        interactorImpl.disposeDisposable();
    }
}


