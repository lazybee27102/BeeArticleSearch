package com.coderschool.beeiscoding.beearticlesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coderschool.beeiscoding.beearticlesearch.CustomRecyclerView.ArticleAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    //APIKEY
    private static final String API_KEY = "95cd7c55cbeb555630d6e03f0406648d:4:74744947";
    private static final String url = "http://api.nytimes.com/svc/search/v2/articlesearch.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerWidgets();
        HandleWidget();

        AsyncHttpClient client = new AsyncHttpClient();
        String now = getTimeNow();
        Toast.makeText(MainActivity.this, now, Toast.LENGTH_SHORT).show();

        RequestParams params = new RequestParams();
        params.put("api-key", API_KEY);
        params.put("begin_date", now);
        params.put("end_date", now);

        client.get(url,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                Log.d("DEBUGVALUE",response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });



    }

    private String getTimeNow()
    {
        Time time = new Time();
        time.setToNow();
        String now = time.year+""+FormatDate(time.month+1)+""+FormatDate(time.monthDay)+"";
        return now;
    }

    private String FormatDate(int s)
    {
       return (s<10)? ("0"+s) : String.valueOf(s);
    }


    private void HandleWidget() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void registerWidgets() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_articles);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified())
                    searchView.setIconified(true);
                menuItem.collapseActionView();
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
}
