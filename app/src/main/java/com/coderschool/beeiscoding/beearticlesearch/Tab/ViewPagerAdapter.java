package com.coderschool.beeiscoding.beearticlesearch.Tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by beeiscoding on 19/03/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    final int PAGE_COUNT = 14;
    private static final String[] tabTitles =new String[]{"home","world","national","politics","business","technology","science","health","sports"
    ,"arts","fashion","dining","travel","magazine"};

    public ViewPagerAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.CreateNewPage(tabTitles[position]);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
