package com.tcrj.micro.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.tcrj.micro.activity.jzfp.tzbbFregment;
import com.tcrj.micro.activity.jzfp.wyfpFregment;


/**
 * Created by leict on 2018/4/19.
 */

public class jzfpFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"我要扶贫"};

    public jzfpFragmentPagerAdapter(FragmentManager fm ) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            return new wyfpFregment();
// else if (position == 1) {
//            return new wyfpFregment();
//        }else if (position == 2){
//            return new wyfpFregment();
//        }else if (position == 3){
//            return new wyfpFregment();
//        }else if (position == 4){
//            return new wyfpFregment();
//        }else if (position == 5){
//            return new wyfpFregment();
//        }
//        return new tzbbFregment();
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

