package com.example.factorynews.screen_second;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.factorynews.model.Article;

import java.util.List;

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Article> articleList;

    public CustomPagerAdapter(FragmentManager fm, Context context, List<Article> articleList) {
        super(fm);
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public Fragment getItem(int i) {
        PagerFragment pagerFragment = new PagerFragment();
        Bundle bundle = new Bundle();
        Article articleItem = articleList.get(i);
        bundle.putSerializable(PagerFragment.EXTRA_ARTICLE_ITEM, articleItem);
        pagerFragment.setArguments(bundle);
        return pagerFragment;
    }


    @Override
    public int getCount() {
        return articleList.size();
    }
}

