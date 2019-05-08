package com.tcrj.micro.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.tcrj.micro.activity.grRegisteFregment;
import com.tcrj.micro.activity.gsjRegisteFregment;
import com.tcrj.micro.activity.qyRegisteFregment;


/**
 * Created by leict on 2018/4/19.
 */

public class registeFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"个人","企业","工商局内部"};

    public registeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return new grRegisteFregment();
        }else if(position == 2){
            return  new gsjRegisteFregment();
        }
        return new qyRegisteFregment();
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

