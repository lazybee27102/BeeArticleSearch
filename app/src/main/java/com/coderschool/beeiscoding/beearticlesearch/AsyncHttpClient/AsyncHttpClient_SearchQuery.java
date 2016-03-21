package com.coderschool.beeiscoding.beearticlesearch.AsyncHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.coderschool.beeiscoding.beearticlesearch.FilterResult.Article;
import com.coderschool.beeiscoding.beearticlesearch.FilterResult.FilterResult;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by beeiscoding on 20/03/2016.
 */
public class AsyncHttpClient_SearchQuery {
    private static final String API_KEY = "95cd7c55cbeb555630d6e03f0406648d:4:74744947";
    private static final String url = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
    ArrayList<Article> articles = new ArrayList<>();
    private getResponse delegate;
    private ProgressDialog progressDialog;
    private Context context;
    private FilterResult filterResult;

    public AsyncHttpClient_SearchQuery(Context context, FilterResult filterResult, getResponse delegate) {
        this.context = context;
        this.filterResult = filterResult;
        this.delegate = delegate;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Searching.....");
        progressDialog.show();
    }

    public boolean checkString(String s) {
        return (s != null && !s.isEmpty());
    }

    public void callRequest() {
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        //Check
        if (checkString(filterResult.getQuery())) {
            params.put("q", filterResult.getQuery());
            //Log.d("DEBUG_VALUE", filterResult.getQuery());
        }


        if (checkString(filterResult.getBegindate()) && checkString(filterResult.getEnddate())) {
            params.put("begin_date", filterResult.getBegindate());
            params.put("end_date", filterResult.getEnddate());
            Log.d("DEBUG_VALUE", filterResult.getBegindate() + filterResult.getEnddate());
        }

        if (checkString(filterResult.getSort())) {
            params.put("sort", filterResult.getSort().toLowerCase());
            Log.d("DEBUG_VALUE", filterResult.getSort());
        }


        if (checkString(filterResult.getSection())) {
            params.put("fq", "news_desk:(" + filterResult.getSection() + ")");
            Log.d("DEBUG_VALUE", filterResult.getSection());
        }


        params.put("api-key", API_KEY);

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    int hint = response.getJSONObject("response").getJSONObject("meta").getInt("hits");
                    if (hint > 0) {
                        JSONArray array = response.getJSONObject("response").getJSONArray("docs");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            if (object.getJSONArray("multimedia").length() != 0) {
                                Article article = new Article();
                                article.setTitle(object.getString("snippet"));
                                article.setTime(object.getString("pub_date"));
                                article.setAuthor(object.getJSONObject("byline").getString("original"));
                                article.setSection(object.getString("section_name"));
                                article.setWebViewURL(object.getString("web_url"));
                                article.setImageURL("http://nytimes.com/" + object.getJSONArray("multimedia").getJSONObject(0).getString("url"));
                                articles.add(article);
                            }

                            delegate.handleResponse(articles);
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }else
                    {
                        Toast.makeText(context, "There is no result!", Toast.LENGTH_SHORT).show();
                    }
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();


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

    public interface getResponse {
        void handleResponse(ArrayList<Article> arrayList);
    }
}
