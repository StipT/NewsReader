package com.example.factorynews.single_article_screen.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.factorynews.R;
import com.example.factorynews.model.data.Article;
import com.example.factorynews.news_list_screen.recycler_adapter.ArticleViewHolder;
import com.example.factorynews.single_article_screen.pager_adapter.CustomPagerAdapter;
import com.example.factorynews.single_article_screen.presenter.SingleArticlePresenterImpl;

import java.util.ArrayList;


public class SingleArticleActivity extends AppCompatActivity implements SingleArticleView {

    public static final int PAGE_LIMIT = 9;
    private static final String TAG = "SingleArticleActivity";
    private ViewPager viewPager;
    private SingleArticlePresenterImpl singleArticlePresenterImpl;
    private CustomPagerAdapter customPagerAdapter;

    @Override
    public void viewPagerSetUp(ArrayList<Article> newsList) {
        viewPager = findViewById(R.id.view_pager);
        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), newsList);
        viewPager.setAdapter(customPagerAdapter);

        int position = getIntent().getIntExtra(ArticleViewHolder.EXTRA_ITEM_POSITION, 0);
        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean isLastArticle() {
        Log.d(TAG, "changeScreen: " + viewPager.getCurrentItem());
        return (viewPager.getCurrentItem() >= PAGE_LIMIT);
    }


    @Override
    public void changeScreen() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_article);

        singleArticlePresenterImpl = new SingleArticlePresenterImpl(this);
        singleArticlePresenterImpl.onCreate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        singleArticlePresenterImpl.onStop();
    }

}
