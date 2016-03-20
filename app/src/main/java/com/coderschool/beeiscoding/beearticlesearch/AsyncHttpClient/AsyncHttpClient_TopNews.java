package com.coderschool.beeiscoding.beearticlesearch.AsyncHttpClient;

import android.content.Context;
import android.util.Log;

import com.coderschool.beeiscoding.beearticlesearch.TopNews;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by beeiscoding on 19/03/2016.
 */
public class AsyncHttpClient_TopNews {
    private String title;
    private static final String API_KEY_TOP_NEWS = "59cc3a1f67c3f3ebc9994106024cd05f:12:74744947";
    private getResponse delegate;
    private ArrayList<TopNews> topNewses = new ArrayList<>();
    private Context context;

    public AsyncHttpClient_TopNews(Context context,String title,getResponse delegate) {
        this.title = title;
        this.delegate = delegate;
        this.context = context;
    }

    public interface getResponse
    {
        void handleResponse(ArrayList<TopNews> arrayList);
    }

    public void callRequest()
    {
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://api.nytimes.com/svc/topstories/v1/" + title+".json?api-key=" +API_KEY_TOP_NEWS;
        client.get(context,url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("DEBUG_VALUE", title);
                try {
                    JSONArray array = response.getJSONArray("results");
                    for (int i = 0; i < array.length() && i < 10; i++) {
                        JSONObject object = array.getJSONObject(i);
                        TopNews topNews = new TopNews();
                        topNews.setSubSection(object.getString("subsection"));
                        topNews.setAuthor(object.getString("byline"));
                        topNews.setPubdate(object.getString("published_date"));
                        topNews.setTitle(object.getString("title"));
                        topNews.setImageURL(object.getJSONArray("multimedia").getJSONObject(3).getString("url"));
                        topNewses.add(topNews);
                    }
                    delegate.handleResponse(topNewses);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
