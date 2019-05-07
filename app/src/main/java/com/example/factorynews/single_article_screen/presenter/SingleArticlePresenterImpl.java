package com.example.factorynews.single_article_screen.presenter;

import android.util.Log;

import com.example.factorynews.model.interactor.InteractorImpl;
import com.example.factorynews.single_article_screen.view.SingleArticleView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;

import static android.support.constraint.Constraints.TAG;

public class SingleArticlePresenterImpl implements SingleArticlePresenter {


    private SingleArticleView singleArticleView;
    private InteractorImpl interactorImpl;
    CompositeDisposable compositeDisposable;

    public SingleArticlePresenterImpl(SingleArticleView singleArticleView) {
        this.singleArticleView = singleArticleView;
        interactorImpl = new InteractorImpl();
    }

    @Override
    public void onCreate() {
        if (singleArticleView != null) {
            singleArticleView.viewPagerSetUp(interactorImpl.getNewsFromRealm());
            intervalScreenChange();
        }
    }

    public void intervalScreenChange() {

        ResourceObserver<Long> timeObserver = new ResourceObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                if (singleArticleView.isLastArticle()) {
                    compositeDisposable.dispose();
                    Log.d(TAG, "onNext: Screens have stopped scrolling");
                } else {
                    singleArticleView.changeScreen();
                    Log.d(TAG, "onNext: Screen changed");
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Task completed");
            }
        };

        Disposable disposable = Observable
                .interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(timeObserver);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
