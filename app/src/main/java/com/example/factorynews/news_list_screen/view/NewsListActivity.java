package com.example.factorynews.news_list_screen.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.factorynews.R;
import com.example.factorynews.model.data.Article;
import com.example.factorynews.news_list_screen.OnClickedListener;
import com.example.factorynews.news_list_screen.presenter.NewsListPresenterImpl;
import com.example.factorynews.news_list_screen.recycler_adapter.RecyclerAdapter;
import com.example.factorynews.single_article_screen.view.SingleArticleActivity;

import java.util.List;


public class NewsListActivity extends AppCompatActivity implements NewsListView {

    public static final String EXTRA_ITEM_POSITION = "EXTRA_ITEM_POSITION";

    private NewsListPresenterImpl newsListPresenterImpl;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ProgressBar progressBar;

    @Override
    public void showProgressBar() {
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindRecyclerView() {
        if (recyclerAdapter == null) {
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerAdapter = new RecyclerAdapter(new OnClickedListener() {
                @Override
                public void onClicked(int position) {
                    goToSingleArticleActivity(position);
                }
            });
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    @Override
    public void updateItems(List<Article> articleList) {
        recyclerAdapter.addItems(articleList);
    }

    @Override
    public void goToSingleArticleActivity(int position) {
        Intent intent = new Intent(this, SingleArticleActivity.class);
        intent.putExtra(EXTRA_ITEM_POSITION, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void showDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogError dialogError = DialogError.newInstance();
        dialogError.show(fm, "error_dialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        newsListPresenterImpl = new NewsListPresenterImpl(this);
        newsListPresenterImpl.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsListPresenterImpl.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        newsListPresenterImpl.onResume();
    }
}
