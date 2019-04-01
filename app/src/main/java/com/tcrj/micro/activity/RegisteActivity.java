package com.tcrj.micro.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcrj.micro.R;
import com.tcrj.micro.adpater.registeFragmentPagerAdapter;
import com.tcrj.micro.application.BaseActivity;


public class RegisteActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    TextView txtTitle;
    ImageView btnback;

    private registeFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        initView();
        getData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnback:
                finish();
                break;
        }
    }


    public void initView() {

        mTabLayout = findViewById(R.id.tab_main);
        mViewPager = findViewById(R.id.vp_main);
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);

        myFragmentPagerAdapter = new registeFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout和ViewPager绑定在一起，使双方各自的改变都能直接影响另一方，解放了开发人员对双方变动事件的监听
        mTabLayout.setupWithViewPager(mViewPager);
        txtTitle.setText("注册");
        btnback.setOnClickListener(this);
    }

    public void getData() {

    }
}
