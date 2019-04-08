package com.example.factorynews.screen_first;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.example.factorynews.R;
import com.example.factorynews.model.Article;
import com.example.factorynews.network.FetchNews;
import io.realm.Realm;
import io.realm.RealmList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Realm realm;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        realm = Realm.getDefaultInstance();
        FetchNews fetchNews = new FetchNews();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                fetchNews.enqueueNews(new FetchNews.RetrofitListener() {
                @Override
                public void onCompletion(RealmList<Article> list) {
                    realm.beginTransaction();
                    realm.deleteAll();
                    realm.copyToRealmOrUpdate(list);
                    realm.commitTransaction();
                    recyclerAdapter = new RecyclerAdapter(list, getApplicationContext());
                    recyclerView.setAdapter(recyclerAdapter);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

    private void showDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogError dialogError = DialogError.newInstance();
        dialogError.show(fm, "error_dialog");
    }

}
