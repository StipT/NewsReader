package com.example.factorynews.di.module;

import com.example.factorynews.di.scope.ActivityScope;
import com.example.factorynews.news_list_screen.di.NewsListModule;
import com.example.factorynews.news_list_screen.view.NewsListActivity;
import com.example.factorynews.single_article_screen.di.SingleArticleModule;
import com.example.factorynews.single_article_screen.view.SingleArticleActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = NewsListModule.class)
    abstract NewsListActivity contributeNewsListActivity();


    @ActivityScope
    @ContributesAndroidInjector(modules = SingleArticleModule.class)
    abstract SingleArticleActivity singleArticleActivity();

}
