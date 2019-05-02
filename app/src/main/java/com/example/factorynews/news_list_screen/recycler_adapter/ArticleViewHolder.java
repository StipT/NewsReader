package com.example.factorynews.news_list_screen.recycler_adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.factorynews.R;
import com.example.factorynews.model.data.Article;
import com.example.factorynews.news_list_screen.view.NewsListActivity;
import com.example.factorynews.single_article_screen.view.SingleArticleActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_ITEM_POSITION = "EXTRA_ITEM_POSITION";

    private TextView title;
    private ImageView thumbnail;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.item_title);
        this.thumbnail = itemView.findViewById(R.id.item_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        NewsListActivity newsListActivity = new NewsListActivity();
        Intent intent = new Intent(v.getContext(), SingleArticleActivity.class);
        intent.putExtra(EXTRA_ITEM_POSITION, getAdapterPosition());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        v.getContext().startActivity(intent);
    }

    public void onBind(List<Article> articleList, int position) {
        Article articleItem = articleList.get(position);
        title.setText(articleItem.getTitle());
        Picasso.with(itemView.getContext())
                .load(articleItem.getUrlToImage())
                .into(thumbnail);
    }

}






