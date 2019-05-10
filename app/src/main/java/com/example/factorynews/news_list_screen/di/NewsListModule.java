package com.example.factorynews.news_list_screen.di;

import com.example.factorynews.di.scope.ActivityScope;
import com.example.factorynews.model.interactor.Interactor;
import com.example.factorynews.model.interactor.InteractorImpl;
import com.example.factorynews.news_list_screen.presenter.NewsListPresenter;
import com.example.factorynews.news_list_screen.presenter.NewsListPresenterImpl;
import com.example.factorynews.news_list_screen.view.NewsListActivity;
import com.example.factorynews.news_list_screen.view.NewsListView;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsListModule {

    @Provides
    @ActivityScope
    NewsListView newsListView(NewsListActivity newsListActivity) {
        return newsListActivity;
    }

    @ActivityScope
    @Provides
    NewsListPresenter newsListPresenter(NewsListPresenterImpl newsListPresenter) {
        return newsListPresenter;
    }

    @ActivityScope
    @Provides
    Interactor provideInteractorImpl(InteractorImpl interactorImpl) {
        return interactorImpl;
    }

}
