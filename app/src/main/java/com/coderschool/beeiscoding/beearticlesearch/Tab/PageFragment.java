package com.coderschool.beeiscoding.beearticlesearch.Tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderschool.beeiscoding.beearticlesearch.AsyncHttpClient.AsyncHttpClient_TopNews;
import com.coderschool.beeiscoding.beearticlesearch.CustomRecyclerView.TopNewsAdapter;
import com.coderschool.beeiscoding.beearticlesearch.MainActivity;
import com.coderschool.beeiscoding.beearticlesearch.R;
import com.coderschool.beeiscoding.beearticlesearch.TopNews;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    public static final String argument_fragment_title = "argument_fragment_title";
    private RecyclerView recyclerView;
    private TopNewsAdapter adapter;
    private ArrayList<TopNews> normal_topNewses;
    private TopNews main_articles;
    private String section;
    private TextView textView_noArticles;

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
        //Log.d("DEBUG_VAL","onCreateView");


        View rootView =  inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView_articles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        textView_noArticles = (TextView)rootView.findViewById(R.id.textView_no_article);

        AsyncHttpClient_TopNews asyncHttpClient_topNews = new AsyncHttpClient_TopNews(getContext(), getArguments().getString(argument_fragment_title), new AsyncHttpClient_TopNews.getResponse() {
            @Override
            public void handleResponse(ArrayList<TopNews> arrayList) {
                if(arrayList.size()!=0)
                {
                    adapter = new TopNewsAdapter(getContext(),arrayList);
                    recyclerView.setVisibility(View.VISIBLE);
                    textView_noArticles.setVisibility(View.GONE);
                    recyclerView.setAdapter(adapter);
                }else
                {
                    recyclerView.setVisibility(View.GONE);
                    textView_noArticles.setVisibility(View.VISIBLE);
                }


            }
        });
        asyncHttpClient_topNews.callRequest();
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            String title = getArguments().getString(argument_fragment_title);
            int idResource = getActivity().getResources().getIdentifier(title, "drawable", getActivity().getPackageName());
            ((MainActivity)this.getActivity()).getImageView_expanded().setBackgroundResource(idResource);

        }

    }
}
