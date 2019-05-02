package com.example.factorynews.single_article_screen.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.factorynews.R;
import com.example.factorynews.model.data.Article;
import com.example.factorynews.news_list_screen.recycler_adapter.ArticleViewHolder;
import com.example.factorynews.single_article_screen.pager_adapter.CustomPagerAdapter;
import com.example.factorynews.single_article_screen.presenter.SingleArticlePresenterImpl;

import java.util.ArrayList;


public class SingleArticleActivity extends AppCompatActivity implements SingleArticleView {

    private ViewPager viewPager;

    @Override
    public void viewPagerSetUp(ArrayList<Article> newsList) {
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager(), newsList));

        int position = getIntent().getIntExtra(ArticleViewHolder.EXTRA_ITEM_POSITION, 0);
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_article);
        SingleArticlePresenterImpl singleArticlePresenterImpl = new SingleArticlePresenterImpl(this);
        singleArticlePresenterImpl.onCreate();
    }
}
