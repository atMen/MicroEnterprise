package com.tcrj.micro.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.newui.pageview.PageView;
import com.newui.pageview.PageViewAdapter;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.news.NewsFindActivity;
import com.tcrj.micro.activity.support.Czzjzc;
import com.tcrj.micro.activity.support.Jrzc;
import com.tcrj.micro.activity.support.Qt;
import com.tcrj.micro.activity.support.Shyh;
import com.tcrj.micro.activity.support.SupportFindActivity;
import com.tcrj.micro.adpater.xwdtFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class SupportFragment extends Fragment {
    private View fragmetView;
    private TextView title_tv;
    private ImageView search_img;
    private ViewPager viewPager = null;
    private TabLayout tab_main;
    private List<PageView> pageViews = null;
//    private RadioGroup selectGroup;
//    private RadioButton select_czzjzc;
//    private RadioButton select_jrzc;
//    private RadioButton select_shyh;
//    private RadioButton select_qt;
    private Czzjzc czzjZc = null;
    private Jrzc jrzc = null;
    private Shyh shyh = null;
    private Qt qt = null;
    private int currNum = 0;
    private int oldNum = 0;
    private Dialog progressDialog;

    private xwdtFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.support_fragment, container, false);
        initView();
        initViewPager();
        return fragmetView;

    }

    public void initView() {
        title_tv = (TextView) fragmetView.findViewById(R.id.txtTitle);
        search_img = (ImageView) fragmetView.findViewById(R.id.btnsearch);
        viewPager = (ViewPager) fragmetView.findViewById(R.id.select_viewpager);
        tab_main = (TabLayout) fragmetView.findViewById(R.id.tab_main);
//        selectGroup = (RadioGroup) fragmetView.findViewById(R.id.select_group);
//        select_czzjzc = (RadioButton) fragmetView.findViewById(R.id.select_czzjzc);
//        select_jrzc = (RadioButton) fragmetView.findViewById(R.id.select_jrzc);
//        select_shyh = (RadioButton) fragmetView.findViewById(R.id.select_shyh);
//        select_qt = (RadioButton) fragmetView.findViewById(R.id.select_qt);
        title_tv.setText("申请扶持");
        search_img.setVisibility(View.VISIBLE);
        search_img.setOnClickListener(new OnClick());
//        selectGroup.setOnCheckedChangeListener(new onCheckedChanged());
    }

    private void initViewPager() {
//        pageViews = new ArrayList<PageView>();
//        czzjZc = new Czzjzc(getActivity());
//        jrzc = new Jrzc(getActivity());
//        shyh = new Shyh(getActivity());
//        qt = new Qt(getActivity());
//        pageViews.add(czzjZc);
//        pageViews.add(jrzc);
//        pageViews.add(shyh);
//        pageViews.add(qt);
//        viewPager.setAdapter(new PageViewAdapter(pageViews));
//        viewPager.setCurrentItem(0);
//        PageView view = pageViews.get(0);
//        view.onCreate(null);
//        view.visible();
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int currentNum) {
//                setButtonPerformClick(currentNum);
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//                /** arg0==0时change 结束 **/
//                if (arg0 == 0) {
//                    if (oldNum != currNum) {
//                        PageView oldView = pageViews.get(oldNum);
//                        if (oldView.isCreated()) {
//                            oldView.disVisible();
//                        }
//                        PageView newView = pageViews.get(currNum);
//                        if (!newView.isCreated()) {
//                            newView.onCreate(null);
//                        }
//                        newView.visible();
//                    }
//                }
//            }
//        });
        myFragmentPagerAdapter = new xwdtFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout和ViewPager绑定在一起，使双方各自的改变都能直接影响另一方，解放了开发人员对双方变动事件的监听
        tab_main.setupWithViewPager(viewPager);

    }

//    class onCheckedChanged implements OnCheckedChangeListener {
//
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            int id = 0;
//            if (checkedId == R.id.select_czzjzc) {
//                id = 0;
//            } else if (checkedId == R.id.select_jrzc) {
//                id = 1;
//            }else if (checkedId == R.id.select_shyh) {
//                id = 2;
//            }else if (checkedId == R.id.select_qt) {
//                id = 3;
//            }
//            oldNum = currNum;
//            currNum = id;
//            viewPager.setCurrentItem(id);
//        }
//
//    }

//    public void setButtonPerformClick(int position) {
//        if (position == 0) {
//            select_czzjzc.performClick();
//        }else if (position == 1) {
//            select_jrzc.performClick();
//        }else if (position == 2) {
//            select_shyh.performClick();
//        }else if (position == 3) {
//            select_qt.performClick();
//        }
//    }

    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btnsearch:
                    intent.setClass(getActivity(), SupportFindActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    }

    // 网络加载进度条
    public void showProgressDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText("正在加载，请稍候...");
        progressDialog = new Dialog(getActivity(), R.style.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();
    }

    public void dismisProgressDialog() {
        if (progressDialog == null) {
            return;
        } else {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;

            }
        }
    }

    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 9:
                    dismisProgressDialog();
                    Toast.makeText(getActivity(), "服务器异常，获取数据失败",
                            Toast.LENGTH_LONG).show();
                    break;
                case 10: // 没有网络连接
                    dismisProgressDialog();
                    Toast.makeText(getActivity(), "当前没有网络连接", Toast.LENGTH_LONG)
                            .show();
                    break;
                case 11: // 获取数据失败
                    dismisProgressDialog();
                    Toast.makeText(getActivity(), "服务器异常，获取数据失败",
                            Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };

}
