package com.tcrj.micro.activity.left;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.newui.hzwlistview.xlist.XListView;
import com.newui.hzwlistview.xlist.XListView.IXListViewListener;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.FuChiPagerAdapter;
import com.tcrj.micro.adpater.FuchiListAdapter;
import com.tcrj.micro.adpater.LeftListAdapter;
import com.tcrj.micro.adpater.xwdtFragmentPagerAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.FuchiEntity;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.OnClickListener;
import static android.widget.AdapterView.OnItemClickListener;

public class LeftListActivity2 extends AppCompatActivity {

    private ViewPager viewPager = null;
    private TabLayout tab_main;

    private String title;
    private TextView tvtitle;
    private ImageView backBtn;
    private ImageView btnsearch;
    private FuChiPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left2_list);
        initView();
    }


    public void initView() {

        viewPager = (ViewPager) findViewById(R.id.select_viewpager);
        tab_main = (TabLayout) findViewById(R.id.tab_main);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        btnsearch = (ImageView) findViewById(R.id.btnsearch);
        backBtn.setVisibility(View.VISIBLE);
        btnsearch.setVisibility(View.GONE);
        tvtitle.setText(title);

        backBtn.setOnClickListener(new OnClick());
        btnsearch.setOnClickListener(new OnClick());


        myFragmentPagerAdapter = new FuChiPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout和ViewPager绑定在一起，使双方各自的改变都能直接影响另一方，解放了开发人员对双方变动事件的监听
        tab_main.setupWithViewPager(viewPager);
    }


    class OnClick implements  OnClickListener{

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
    }
}
