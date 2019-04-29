package com.example.factorynews.screen_first;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.factorynews.R;
import com.example.factorynews.model.Article;
import com.example.factorynews.model.News;
import com.example.factorynews.network.FetchNews;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Realm realm;
    ProgressBar progressBar;
    RealmList<Article> newsList = new RealmList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        enqueueNews();
    }


    private void showDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogError dialogError = DialogError.newInstance();
        dialogError.show(fm, "error_dialog");
    }


    private void enqueueNews() {
        Call<News> call = FetchNews.getNewsApi().getNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Response code: " + response.code());
                } else {
                    try {
                        News news = response.body();

                        Log.d(TAG, "onResponse: News status " + news.getStatus());
                        Log.d(TAG, "onResponse: Source " + news.getSource());
                        for (Article r : news.getArticles()) {
                            newsList.add(r);
                            Log.d(TAG, "onResponse: ==> " + r.toString());
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "onResponse: newsList  " + newsList);

                realm.beginTransaction();
                realm.deleteAll();
                realm.copyToRealmOrUpdate(newsList);
                realm.commitTransaction();
                setRecyclerAdapter(newsList);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d(TAG, "onFailure: Enqueue failed");
                t.printStackTrace();
            }
        });

    }

    private void setRecyclerAdapter(List list) {
        if (recyclerAdapter == null) {
            recyclerAdapter = new RecyclerAdapter(list, getApplicationContext());
        }
        recyclerView.setAdapter(recyclerAdapter);
    }
}
