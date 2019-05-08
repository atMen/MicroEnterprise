package com.tcrj.micro.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.tcrj.micro.activity.jrtz.jralFragment;
import com.tcrj.micro.activity.jrtz.jrcpFragment;
import com.tcrj.micro.activity.jzfp.tzbbFregment;
import com.tcrj.micro.activity.jzfp.wyfpFregment;


/**
 * Created by leict on 2018/4/19.
 */

public class jrtzFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"金融案例","金融产品"};

    public jrtzFragmentPagerAdapter(FragmentManager fm ) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new jralFragment();
        }

        return new jrcpFragment();
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

