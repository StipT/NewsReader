package com.example.factorynews.single_article_screen.presenter;

import com.example.factorynews.model.interactor.InteractorImpl;
import com.example.factorynews.single_article_screen.view.SingleArticleView;

public class SingleArticlePresenterImpl implements SingleArticlePresenter {

    private SingleArticleView singleArticleView;
    private InteractorImpl interactorImpl;

    public SingleArticlePresenterImpl(SingleArticleView singleArticleView) {
        this.singleArticleView = singleArticleView;
        interactorImpl = new InteractorImpl();
    }

    @Override
    public void onCreate() {
        if (singleArticleView != null) {
            singleArticleView.viewPagerSetUp(interactorImpl.getNewsFromRealm());
        }
    }
}
