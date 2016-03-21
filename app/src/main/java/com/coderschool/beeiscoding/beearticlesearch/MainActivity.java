package com.coderschool.beeiscoding.beearticlesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.coderschool.beeiscoding.beearticlesearch.FilterResult.FoundArticlesActivity;
import com.coderschool.beeiscoding.beearticlesearch.Tab.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    //APIKEY

    private Toolbar toolbar;
    private ImageView imageView_expanded;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerWidgets();
        HandleWidget();
    }


    private void HandleWidget() {
    }

    private void registerWidgets() {
        imageView_expanded = (ImageView) findViewById(R.id.expandedImage);
        imageView_expanded.setBackgroundResource(R.drawable.home);
        //Tab
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        tablayout = (TabLayout) findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified())
                    searchView.setIconified(true);
                menuItem.collapseActionView();

                if (!query.equals("") && query.length() != 0) {
                    Intent i = new Intent(MainActivity.this,FoundArticlesActivity.class);
                    i.putExtra("QUERY",query);
                    startActivity(i);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public ImageView getImageView_expanded() {
        return imageView_expanded;
    }
}
