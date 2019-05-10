package com.example.factorynews.news_list_screen.recycler_adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.factorynews.R;
import com.example.factorynews.model.data.Article;
import com.example.factorynews.news_list_screen.OnClickedListener;

import java.util.List;

import javax.inject.Inject;


public class RecyclerAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<Article> articleList;


    private OnClickedListener onClickedListener;

    @Inject
    public RecyclerAdapter(OnClickedListener onClickedListener) {
        this.onClickedListener = onClickedListener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ArticleViewHolder(view, onClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder articleViewHolder, int i) {
        if (articleList != null) {
            articleViewHolder.onBind(articleList, i);
        }
    }

    @Override
    public int getItemCount() {
        return ((articleList != null) && (articleList.size() != 0) ? articleList.size() : 0);
    }

    public void addItems(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

}
