package com.example.factorynews.news_list_screen.di;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.example.factorynews.di.scope.ActivityScope;
import com.example.factorynews.model.interactor.Interactor;
import com.example.factorynews.model.interactor.InteractorImpl;
import com.example.factorynews.news_list_screen.OnClickedListener;
import com.example.factorynews.news_list_screen.presenter.NewsListPresenter;
import com.example.factorynews.news_list_screen.presenter.NewsListPresenterImpl;
import com.example.factorynews.news_list_screen.recycler_adapter.RecyclerAdapter;
import com.example.factorynews.news_list_screen.view.NewsListActivity;
import com.example.factorynews.news_list_screen.view.NewsListView;
import com.example.factorynews.single_article_screen.view.SingleArticleActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsListModule {

    @Provides
    @ActivityScope
    NewsListView provideView(NewsListActivity newsListActivity) {
        return newsListActivity;
    }

    @ActivityScope
    @Provides
    NewsListPresenter providePresenter(NewsListPresenterImpl newsListPresenter) {
        return newsListPresenter;
    }

    @ActivityScope
    @Provides
    Interactor provideInteractor(InteractorImpl interactorImpl) {
        return interactorImpl;
    }

    @ActivityScope
    @Provides
    RecyclerAdapter provideRecyclerAdapter(OnClickedListener onClickedListener) {
        return new RecyclerAdapter(onClickedListener);
    }

    @Provides
    @ActivityScope
    OnClickedListener provideOnClickedListener(NewsListActivity newsListActivity) {
        return position -> newsListActivity.goToSingleArticleActivity(position);
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(NewsListActivity newsListActivity) {
        return new LinearLayoutManager(newsListActivity);
    }

    @Provides
    @ActivityScope
    Intent provideIntent(NewsListActivity newsListActivity) {
        return new Intent(newsListActivity, SingleArticleActivity.class);

    }
}
