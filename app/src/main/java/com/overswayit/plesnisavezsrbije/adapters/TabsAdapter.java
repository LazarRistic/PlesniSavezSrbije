package com.overswayit.plesnisavezsrbije.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/3/2016.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> pageList;

    public TabsAdapter (FragmentManager fragmentManager) {
        super(fragmentManager);
        setPageList(new ArrayList<Fragment>());
    }

    @Override
    public Fragment getItem(int position) {
        return getPageList().get(position);
    }

    @Override
    public int getCount() {
        return getPageList().size();
    }

    public ArrayList<Fragment> getPageList() {
        return pageList;
    }

    public void setPageList(ArrayList<Fragment> pageList) {
        this.pageList = pageList;
    }
}
