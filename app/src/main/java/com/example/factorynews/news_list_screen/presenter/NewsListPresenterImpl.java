package com.example.factorynews.news_list_screen.presenter;

import com.example.factorynews.model.data.Article;
import com.example.factorynews.model.interactor.InteractorImpl;
import com.example.factorynews.news_list_screen.view.NewsListView;

import java.util.ArrayList;

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
            interactorImpl.fetchNews(new InteractorImpl.OnFinishedListener() {

                @Override
                public void onFinished(ArrayList<Article> items) {
                    interactorImpl.storeNewsOnRealm(items);
                    newsListView.updateItems(interactorImpl.getNewsFromRealm());
                }
            });
            newsListView.hideProgressBar();
        }
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
        interactorImpl.closeRetrofit();
    }
}


