package com.example.factorynews.screen_second;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.factorynews.R;
import com.example.factorynews.model.Article;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {

    private TextView pagerTitle;
    private TextView pagerDescription;
    private ImageView pagerImage;


    public PagerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Article articleItem = (Article) getArguments().getSerializable("articleItem");
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
