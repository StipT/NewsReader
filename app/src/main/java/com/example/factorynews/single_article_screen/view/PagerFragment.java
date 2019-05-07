package com.example.factorynews.single_article_screen.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.factorynews.R;
import com.example.factorynews.model.data.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {

    public static final String EXTRA_ARTICLE_ITEM = "EXTRA_ARTICLE_ITEM";

    private TextView pagerTitle;
    private TextView pagerDescription;
    private ImageView pagerImage;


    public static PagerFragment newInstance(ArrayList<Article> articleList, int i) {

        Bundle bundle = new Bundle();
        PagerFragment fragment = new PagerFragment();
        Article articleItem = articleList.get(i);
        bundle.putSerializable(PagerFragment.EXTRA_ARTICLE_ITEM, articleItem);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Article articleItem = (Article) getArguments().getSerializable(EXTRA_ARTICLE_ITEM);
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        pagerTitle = view.findViewById(R.id.pager_title);
        pagerDescription = view.findViewById(R.id.pager_description);
        pagerImage = view.findViewById(R.id.pager_image);

        pagerTitle.setText(articleItem.getTitle());
        pagerDescription.setText(articleItem.getDescription());
        Picasso.with(getContext()).load(articleItem.getUrlToImage()).into(pagerImage);
        return view;
    }
}
