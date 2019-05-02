package com.example.factorynews.news_list_screen.view;

import com.example.factorynews.model.data.Article;

import java.util.List;

public interface NewsListView {

    void showProgressBar();

    void hideProgressBar();

    void bindRecyclerView();

    void updateItems(List<Article> articleList);

    void goToSingleArticleActivity(int position);

    void showDialog();

}
