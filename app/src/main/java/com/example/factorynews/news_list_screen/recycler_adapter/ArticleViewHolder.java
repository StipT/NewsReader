package com.example.factorynews.news_list_screen.recycler_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.factorynews.R;
import com.example.factorynews.model.data.Article;
import com.example.factorynews.news_list_screen.OnClickedListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_ITEM_POSITION = "EXTRA_ITEM_POSITION";

    private TextView title;
    private ImageView thumbnail;
    private OnClickedListener onClickedListener;

    public ArticleViewHolder(@NonNull View itemView, OnClickedListener onClickedListener) {
        super(itemView);
        this.title = itemView.findViewById(R.id.item_title);
        this.thumbnail = itemView.findViewById(R.id.item_image);
        this.onClickedListener = onClickedListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickedListener.onClicked(getAdapterPosition());
    }

    public void onBind(List<Article> articleList, int position) {
        Article articleItem = articleList.get(position);
        title.setText(articleItem.getTitle());
        Picasso.with(itemView.getContext())
                .load(articleItem.getUrlToImage())
                .into(thumbnail);
    }

}






