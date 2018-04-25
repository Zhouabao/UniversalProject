package com.yuzhua.shoppingdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuZhC
 * @create 2018/4/24
 * @Describe
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private  List<Fragment> mFragmentList = new ArrayList<>();

    private FragmentManager manager;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        manager = fm;
    }

    public ViewPagerAdapter(FragmentManager supportFragmentManager, ArrayList<Fragment> fragments) {
        super(supportFragmentManager);
        manager = supportFragmentManager;
        mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}