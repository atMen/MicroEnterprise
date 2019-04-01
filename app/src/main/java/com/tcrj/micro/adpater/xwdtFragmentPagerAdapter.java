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

public class xwdtFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"工商政策支持", "人力资源", "财政资金支持", "金融支持", "税收优惠",
            "注册登记", "其他"};

    public xwdtFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }




    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return  SqfcFragment.newInstance("qmYb2u");
        } else if (position == 1) {
            return SqfcFragment.newInstance("RBVBBr");
        }else if (position == 2){
            return Jrzc.newInstance("QVvQZv");
        }else if (position == 3){
            return Jrzc.newInstance("ZbaQjq");
        }else if (position == 4){
            return Jrzc.newInstance("e6RFZf");
        }else if (position == 5){
            return SqfcFragment.newInstance("YjE7nq");
        }
        return Jrzc.newInstance("7rmeIn");
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

