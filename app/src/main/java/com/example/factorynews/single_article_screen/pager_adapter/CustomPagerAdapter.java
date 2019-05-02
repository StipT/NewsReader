package com.example.factorynews.single_article_screen.pager_adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.factorynews.model.data.Article;

import java.util.ArrayList;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Article> articleList;

    public CustomPagerAdapter(FragmentManager fm, ArrayList<Article> articleList) {
        super(fm);

        this.articleList = articleList;
    }

    @Override
    public Fragment getItem(int i) {
        return PagerFragment.newInstance(articleList, i);
    }

    @Override
    public int getCount() {
        return articleList.size();
    }
}
