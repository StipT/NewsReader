package com.example.factorynews.single_article_screen.view;

import com.example.factorynews.model.data.Article;

import java.util.ArrayList;

public interface SingleArticleView {

    void viewPagerSetUp(ArrayList<Article> newsList);

    void changeScreen();

    boolean isLastArticle();
}
