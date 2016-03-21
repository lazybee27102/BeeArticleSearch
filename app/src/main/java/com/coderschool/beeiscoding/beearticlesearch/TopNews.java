package com.coderschool.beeiscoding.beearticlesearch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by beeiscoding on 19/03/2016.
 */
public class TopNews {
    String title;
    String subSection;
    String author;
    String pubdate;
    String imageURL;
    String webURL;
    String imageHighQuality;

    public String getImageHighQuality() {
        return imageHighQuality;
    }

    public void setImageHighQuality(String imageHighQuality) {
        this.imageHighQuality = imageHighQuality;
    }

    public TopNews(String title, String subSection, String author, String pubdate, String imageURL, String webURL, String imageHighQuality) {
        this.title = title;
        this.subSection = subSection;
        this.author = author;
        this.pubdate = pubdate;
        this.imageURL = imageURL;
        this.webURL = webURL;
        this.imageHighQuality = imageHighQuality;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public TopNews() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubSection() {
        return subSection;
    }

    public void setSubSection(String subSection) {
        this.subSection = subSection;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate.substring(0,10);
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
