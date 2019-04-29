package com.example.factorynews.screen_first;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.factorynews.R;
import com.example.factorynews.model.Article;
import com.example.factorynews.screen_second.SecondActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ArticleViewHolder> {
    private List<Article> articleList;
    private Context context;
    public static final String EXTRA_ITEM_POSITION = "EXTRA_ITEM_POSITION";


    public RecyclerAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ArticleViewHolder articleViewHolder, int i) {
        if (articleList == null || articleList.size() == 0) {
            Log.d(TAG, "onBindViewHolder: Can't reach data");
        } else {
            Article articleItem = articleList.get(i);

            articleViewHolder.title.setText(articleItem.getTitle());
            Picasso.with(context).load(articleItem.getUrlToImage())
                    .into(articleViewHolder.thumbnail);
        }

        articleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("EXTRA_ITEM_POSITION", articleViewHolder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return ((articleList != null) && (articleList.size() !=0) ? articleList.size() : 1);
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumbnail;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.item_title);
            this.thumbnail = itemView.findViewById(R.id.item_image);
        }
    }
}
