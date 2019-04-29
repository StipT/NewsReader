package com.example.factorynews.screen_second;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.factorynews.R;
import com.example.factorynews.model.Article;
import com.example.factorynews.screen_first.RecyclerAdapter;

import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;


public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    private ViewPager viewPager;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        viewPager = findViewById(R.id.view_pager);

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Article> query = realm.where(Article.class).findAll();
        List<Article> articleList = realm.copyFromRealm(query);
        realm.commitTransaction();

        Log.d(TAG, "onCreate: " + articleList.toString());
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager(),
                getApplicationContext(), articleList));
        viewPager.setCurrentItem(getIntent().getIntExtra(RecyclerAdapter.EXTRA_ITEM_POSITION, 0));

    }
}
