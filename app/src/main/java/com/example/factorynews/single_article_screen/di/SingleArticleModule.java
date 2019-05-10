package com.example.factorynews.single_article_screen.di;

import com.example.factorynews.di.scope.ActivityScope;
import com.example.factorynews.model.interactor.Interactor;
import com.example.factorynews.model.interactor.InteractorImpl;
import com.example.factorynews.single_article_screen.pager_adapter.CustomPagerAdapter;
import com.example.factorynews.single_article_screen.presenter.SingleArticlePresenter;
import com.example.factorynews.single_article_screen.presenter.SingleArticlePresenterImpl;
import com.example.factorynews.single_article_screen.view.SingleArticleActivity;
import com.example.factorynews.single_article_screen.view.SingleArticleView;

import dagger.Module;
import dagger.Provides;

@Module
public class SingleArticleModule {

    @ActivityScope
    @Provides
    SingleArticleView provideView(SingleArticleActivity singleArticleActivity) {
        return singleArticleActivity;
    }

    @Provides
    @ActivityScope
    SingleArticlePresenter singlePresenter(SingleArticlePresenterImpl singleArticlePresenter) {
        return singleArticlePresenter;
    }

    @Provides
    @ActivityScope
    Interactor provideInteractor(InteractorImpl interactorImpl) {
        return interactorImpl;
    }

    @Provides
    @ActivityScope
    CustomPagerAdapter provideCustomPagerAdapter(SingleArticleActivity singleArticleActivity) {
        return new CustomPagerAdapter(singleArticleActivity.getSupportFragmentManager());
    }

}
