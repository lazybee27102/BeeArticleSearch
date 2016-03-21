package com.coderschool.beeiscoding.beearticlesearch;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by beeiscoding on 20/03/2016.
 */
public class GlobalVariable {
    public static final String KEY_NEWS_TITLE = "Title";
    public static final String KEY_NEWS_IAMGE_URL = "ImageUrl";
    public static final String KEY_NEWS_OTHER = "Other";
    public static final String KEY_NEWS_SECTION = "Section";
    public static final String KEY_NEWS_BUNDLE = "NewsData";
    public static final String KEY_NEWS_WEBVIEW_URL="WebViewUrl";
    public static final String KEY_NEWS_IMAGE_HIGH_QUALITY = "ImageHighQuality";
    public static int getScreenWidth(Activity activity)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getImageWidth(Activity activity)
    {
        return (int)(getScreenWidth(activity)/2 - 10);
    }






}
