package com.coderschool.beeiscoding.beearticlesearch.Tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coderschool.beeiscoding.beearticlesearch.AsyncHttpClient.AsyncHttpClient_TopNews;
import com.coderschool.beeiscoding.beearticlesearch.CustomRecyclerView.ArticleAdapter;
import com.coderschool.beeiscoding.beearticlesearch.R;
import com.coderschool.beeiscoding.beearticlesearch.TopNews;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    public static final String argument_fragment_title = "argument_fragment_title";
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private ArrayList<TopNews> normal_topNewses;
    private TopNews main_articles;
    private String section;

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment CreateNewPage(String title) {
        PageFragment page = new PageFragment();
        Bundle args = new Bundle();
        args.putString(argument_fragment_title, title);
        page.setArguments(args);
        return page;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section = getArguments().getString(argument_fragment_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false);
       /* AsyncHttpClient_TopNews asyncHttpClient_topNews = new AsyncHttpClient_TopNews(title, new AsyncHttpClient_TopNews.getResponse() {
            @Override
            public void handleResponse(ArrayList<TopNews> arrayList) {
                Toast.makeText(getActivity(), arrayList.get(0).getTitle() + arrayList.get(0).getImageURL()+"", Toast.LENGTH_SHORT).show();
            }
        });
        asyncHttpClient_topNews.callRequest();*/


        //RecyclerView


        //recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView_articles);
        //adapter = new ArticleAdapter(getContext(), normal_topNewses,main_articles);
        //recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView)view.findViewById(R.id.textView_index);
        textView.setText(getArguments().getString(argument_fragment_title));
        Toast.makeText(getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
        /*if(adapter==null)
        {
            AsyncHttpClient_TopNews asyncHttpClient_topNews = new AsyncHttpClient_TopNews(getContext(), getArguments().getString(argument_fragment_title), new AsyncHttpClient_TopNews.getResponse() {
                @Override
                public void handleResponse(ArrayList<TopNews> arrayList) {
                    //Log.d("DEBUGVAL",arrayList.get(0).getTitle());
                }
            });
            asyncHttpClient_topNews.callRequest();
        }*/
    }
}
