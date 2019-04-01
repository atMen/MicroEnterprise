package com.spring.chanba.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spring.chanba.bean.TablayoutTitleEntity;

import java.util.List;

public class FragmentTypeAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<TablayoutTitleEntity> mTitles;

    public FragmentTypeAdapter(FragmentManager fm, List<Fragment> fragments, List<TablayoutTitleEntity> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getCategoryName();
    }
}
