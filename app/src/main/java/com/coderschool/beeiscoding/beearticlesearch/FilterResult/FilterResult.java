package com.coderschool.beeiscoding.beearticlesearch.FilterResult;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by beeiscoding on 21/03/2016.
 */
public class FilterResult {
    String query;
    String section;
    String time;
    String sort;
    String begindate;
    String enddate;

    public FilterResult() {
        section = "";
    }

    public String getBegindate() {
        return begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public FilterResult(String query, String section, String time, String sort) {
        this.query = query;
        this.section = section;
        this.time = time;
        this.sort = sort;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        switch (time)
        {
            case "All time":
            {
                begindate =enddate= "";
            }break;
            case "Today":
            {
                begindate = enddate= getTimeNow();
            }break;
            case "This week":
            {
                begindate = get7daysAgo();
                enddate = getTimeNow();
            }break;
            case "This month":
            {
                begindate = getFirstDayOfThisMonth();
                enddate=getTimeNow();

            }break;
            case "This year":
            {
                begindate = getFirstDateofThisYear();
                enddate = getTimeNow();
            }break;

        }
    }

    private String getTimeNow()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + datePrefix(month) + datePrefix(day);

    }

    private String get7daysAgo()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-7);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + datePrefix(month) + datePrefix(day);
    }

    private String getFirstDayOfThisMonth()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        return year + datePrefix(month) + "01";
    }

    private String getFirstDateofThisYear()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        return year + "0101";
    }









    private String datePrefix(int x)
    {
        return (x<10)? ("0" + x) : String.valueOf(x);
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
