package com.tcrj.micro.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.tcrj.micro.activity.jrtz.gsjjFragment;
import com.tcrj.micro.activity.jrtz.jralFragment;
import com.tcrj.micro.activity.jrtz.jrcpFragment;
import com.tcrj.micro.activity.jzfp.fgfpFragment;
import com.tcrj.micro.activity.jzfp.jdglFragment;


/**
 * Created by leict on 2018/4/19.
 */

public class fpFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"监督管理局扶贫案例","非公经济扶贫","个人扶贫"};

    public fpFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new jdglFragment();
        }else if(position == 1){
            return new fgfpFragment();
        }

        return new fgfpFragment();
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

