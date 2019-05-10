package com.example.factorynews.news_list_screen.presenter;

import com.example.factorynews.model.interactor.Interactor;
import com.example.factorynews.news_list_screen.view.NewsListView;

import javax.inject.Inject;


public class NewsListPresenterImpl implements NewsListPresenter {

    private NewsListView newsListView;
    private Interactor interactor;

    @Inject
    public NewsListPresenterImpl(NewsListView newsListView, Interactor interactor) {
        this.newsListView = newsListView;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        if (newsListView != null) {
            newsListView.showProgressBar();
            newsListView.bindRecyclerView();
            interactor.fetchNews(items -> newsListView.updateItems(items));
            newsListView.hideProgressBar();
        }
    }


    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        interactor.disposeDisposable();
    }
}


