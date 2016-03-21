package com.coderschool.beeiscoding.beearticlesearch.FilterResult;

/**
 * Created by beeiscoding on 20/03/2016.
 */
public class Article {
    String webViewURL;
    String title;
    String imageURL;
    String section;
    String time;
    String author;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Article(String webViewURL, String title, String imageURL, String section, String time, String author) {
        this.webViewURL = webViewURL;
        this.title = title;
        this.imageURL = imageURL;
        this.section = section;
        this.time = time;
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Article() {
    }

    public String getWebViewURL() {
        return webViewURL;
    }

    public void setWebViewURL(String webViewURL) {
        this.webViewURL = webViewURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
