package com.coderschool.beeiscoding.beearticlesearch.FilterResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coderschool.beeiscoding.beearticlesearch.AsyncHttpClient.AsyncHttpClient_SearchQuery;
import com.coderschool.beeiscoding.beearticlesearch.CustomRecyclerView.FoundArticleAdapter;
import com.coderschool.beeiscoding.beearticlesearch.R;

import java.util.ArrayList;

public class FoundArticlesActivity extends AppCompatActivity implements FilterResultFragment.getResponsefromFragment, SectionChooseFragment.getResponseFromDialog {
    String query;
    private Toolbar toolbar;
    private FilterResult result;
    private RecyclerView recyclerView_foundArticles;
    private FoundArticleAdapter adapter;

    public FilterResult getResult() {
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_articles);
        //fetching data
        Intent i = getIntent();
        query = i.getStringExtra("QUERY");

        registerWidget();
        handleEvent();
    }

    private void handleEvent() {

        FilterResult filterResult = new FilterResult();
        filterResult.setQuery(query);
        AsyncHttpClient_SearchQuery asyncHttpClient_searchQuery = new AsyncHttpClient_SearchQuery(this, filterResult, new AsyncHttpClient_SearchQuery.getResponse() {
            @Override
            public void handleResponse(ArrayList<Article> arrayList) {

                adapter = new FoundArticleAdapter(FoundArticlesActivity.this, arrayList);
                recyclerView_foundArticles.setAdapter(adapter);
            }
        });
        asyncHttpClient_searchQuery.callRequest();
    }

    private void registerWidget() {
        result = new FilterResult();

        toolbar = (Toolbar) findViewById(R.id.toolbar_foundArticle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView_foundArticles = (RecyclerView) findViewById(R.id.recyclerView_foundArticles);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView_foundArticles.setHasFixedSize(true);
        recyclerView_foundArticles.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter: {
                showFilterDialog();

            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilterDialog() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FilterResultFragment filterResultFragment = FilterResultFragment.newInstance(query);
        filterResultFragment.show(manager, "FILTER");
    }

    @Override
    public void handleResult(FilterResult filterResult) {
        result.setQuery(filterResult.getQuery());
        result.setSort(filterResult.getSort());
        result.setTime(filterResult.getTime());

        AsyncHttpClient_SearchQuery asyncHttpClient_searchQuery = new AsyncHttpClient_SearchQuery(this, result, new AsyncHttpClient_SearchQuery.getResponse() {
            @Override
            public void handleResponse(ArrayList<Article> arrayList) {

                adapter = new FoundArticleAdapter(FoundArticlesActivity.this, arrayList);
                recyclerView_foundArticles.setAdapter(adapter);


            }
        });

        asyncHttpClient_searchQuery.callRequest();


    }

    @Override
    public void handleResult(String s) {
        this.result.setSection(s);
    }
}
