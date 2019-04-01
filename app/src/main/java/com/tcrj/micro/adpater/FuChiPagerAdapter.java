package com.tcrj.micro.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.tcrj.micro.activity.support.Jrzc;
import com.tcrj.micro.activity.support.SqfcFragment;

/**
 * Created by leict on 2018/4/19.
 */

public class FuChiPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"工商", "人力资源"};

    public FuChiPagerAdapter(FragmentManager fm) {
        super(fm);
    }




    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return  SqfcFragment.newInstance("qmYb2u");
        }
        return SqfcFragment.newInstance("RBVBBr");
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //用来设置tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


}

