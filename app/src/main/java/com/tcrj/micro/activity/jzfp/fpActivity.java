package com.tcrj.micro.activity.jzfp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcrj.micro.R;
import com.tcrj.micro.adpater.fpFragmentPagerAdapter;
import com.tcrj.micro.adpater.jrtzFragmentPagerAdapter;
import com.tcrj.micro.application.BaseActivity;


public class fpActivity extends BaseActivity implements View.OnClickListener {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    TextView txtTitle;
    ImageView btnback;
    private String id;

    private fpFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnback:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwdt_info);


        initView();
        getData();

    }


    @Override
    public void initView() {

        mTabLayout = findViewById(R.id.tab_main);
        mViewPager = findViewById(R.id.vp_main);
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);

        mTabLayout.setVisibility(View.VISIBLE);
        myFragmentPagerAdapter = new fpFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout和ViewPager绑定在一起，使双方各自的改变都能直接影响另一方，解放了开发人员对双方变动事件的监听
        mTabLayout.setupWithViewPager(mViewPager);
        txtTitle.setText("精准扶贫");
        btnback.setOnClickListener(this);
    }

    @Override
    public void getData() {

    }

}
