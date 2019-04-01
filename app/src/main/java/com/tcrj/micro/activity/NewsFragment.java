package com.tcrj.micro.activity;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.toolbox.VolleyUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.newui.tagviewpager.TagViewPager;
import com.spring.chanba.ui.MainActivity;
import com.spring.chanba.ui.center.ProjectApplyActivity;
import com.spring.chanba.ui.home.FinanceServiceActivity;
import com.spring.chanba.ui.home.TrainServiceActivity;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.PermissionListener;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.jzfp.jzfpActivity;
import com.tcrj.micro.activity.news.FgdjActivity;
import com.tcrj.micro.activity.news.GzqActivity;
import com.tcrj.micro.activity.news.NewsDetailActivity;
import com.tcrj.micro.activity.news.NewsFindActivity;
import com.tcrj.micro.activity.news.NewsListActivity;
import com.tcrj.micro.activity.news.StudentActivity;
import com.tcrj.micro.activity.support.EnterpriseActivity;
import com.tcrj.micro.activity.support.EntrepreneurshipActivity;
import com.tcrj.micro.activity.support.FeiGongDangTuanJianActivity;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.MyTextViewQH;
import com.tcrj.micro.view.MyTextViewXH;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsFragment extends Fragment {
    private View fragmetView;
    private TextView title_tv;
    private ImageView left_img;
    private ImageView search_img;
    private MyTextViewQH news_more;
    private TagViewPager viewPager;
    private Dialog progressDialog;
    private ListView list_news;
    private static ArrayList<Object> bannerlist;
    private static ArrayList<InfoEntity> blist;
    private static ArrayList<InfoEntity> list;
    private NewsListAdapter adapter;
    private MyTextViewXH gzq;
    private MyTextViewXH dxs;
    private MyTextViewXH fgdj;
    private MyTextViewXH zxpx;
    private MyTextViewXH jrtz;
    private MyTextViewXH qyk;
    private MyTextViewXH jzfp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.news_fragment, container, false);
        initView();
        getDate();
        return fragmetView;

    }

    public void initView() {
        title_tv = (TextView) fragmetView.findViewById(R.id.txtTitle);
        left_img = (ImageView) fragmetView.findViewById(R.id.leftbutton);
        search_img = (ImageView) fragmetView.findViewById(R.id.btnsearch);
        viewPager = (TagViewPager) fragmetView.findViewById(R.id.mTagViewPager);
        list_news = (ListView) fragmetView.findViewById(R.id.list_news);
        news_more = (MyTextViewQH) fragmetView.findViewById(R.id.news_more);
        gzq = (MyTextViewXH) fragmetView.findViewById(R.id.gzq);
        dxs = (MyTextViewXH) fragmetView.findViewById(R.id.dxs);
        fgdj = (MyTextViewXH) fragmetView.findViewById(R.id.fgdj);
        jzfp = (MyTextViewXH) fragmetView.findViewById(R.id.jzfp);



        zxpx = (MyTextViewXH) fragmetView.findViewById(R.id.zxpx);
        jrtz = (MyTextViewXH) fragmetView.findViewById(R.id.jrtz);
        qyk = (MyTextViewXH) fragmetView.findViewById(R.id.qyk);

        title_tv.setText("资讯");
        left_img.setVisibility(View.VISIBLE);
        search_img.setVisibility(View.VISIBLE);
        initTagViewPager();
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setOnGetView(new TagViewPager.OnGetView() {

            @Override
            public View getView(ViewGroup container, int position) {

                final int index = position;
                ImageView iv = new ImageView(getActivity());
                RequestOptions options = new RequestOptions();
                options.centerCrop();
                Glide.with(getActivity()).load(((HashMap<String, String>) bannerlist.get(position)).get("image")).apply(options).into(iv);
                //iv.setBackgroundResource(R.mipmap.icon);
                container.addView(iv);
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //System.out.println(((HashMap<String, String>) bannerlist.get(index)).get("image"));
                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                        intent.putExtra("id", blist.get(index).getId());
                        startActivity(intent);
                    }
                });
                return iv;


            }
        });
        bannerlist = new ArrayList<Object>();
        list = new ArrayList<InfoEntity>();
        adapter = new NewsListAdapter(getActivity(), list);
        list_news.setAdapter(adapter);
        list_news.setOnItemClickListener(new OnItemClick());

        left_img.setOnClickListener(new OnClick());
        search_img.setOnClickListener(new OnClick());
        news_more.setOnClickListener(new OnClick());
        gzq.setOnClickListener(new OnClick());
        dxs.setOnClickListener(new OnClick());
        fgdj.setOnClickListener(new OnClick());

        zxpx.setOnClickListener(new OnClick());
        jrtz.setOnClickListener(new OnClick());
        qyk.setOnClickListener(new OnClick());
        jzfp.setOnClickListener(new OnClick());

    }

    public void getDate() {
        showProgressDialog();

        //banmer
        VolleyUtil volleyUtil = new VolleyUtil(getActivity(), handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 8);
        params.put("pageindex", 1);
        params.put("siteId", "JfAJJr");
        params.put("id", "FJb6Jr");
        params.put("isThumbUrl", "true");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                //dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                    blist = JsonParse.getInfoList(jsonObject);
                } else {
                    blist = new ArrayList<InfoEntity>();
                }

                for (InfoEntity entity : blist) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("image", entity.getThumbUrl());
                    item.put("text", entity.getTitle());
                    bannerlist.add(item);
                }
                viewPager.setAdapter(bannerlist.size(), bannerlist);

                //要闻列表
                VolleyUtil volleyUtil = new VolleyUtil(getActivity(), handler);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("pagesize", 3);
                params.put("pageindex", 1);
                params.put("siteId", "JfAJJr");
                params.put("id", "FJb6Jr");

                VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        //dismisProgressDialog();
                        Log.d("aa", jsonObject.toString());
                        if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                            list.clear();
                            list.addAll(JsonParse.getInfoList(jsonObject));
                        } else {
                            list.clear();
                        }
                        adapter.notifyDataSetChanged();
                        ViewGroup.LayoutParams params1 = list_news.getLayoutParams();
                        params1.height = calcListHeight(list_news, (NewsListAdapter) list_news.getAdapter()) + (list_news.getDividerHeight() * (list_news.getCount() - 1));
                        list_news.setLayoutParams(params1);

                        //获取城市
                        VolleyUtil volleyUtil = new VolleyUtil(getActivity(), handler);
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("id", "fqyaam");

                        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

                            @Override
                            public void onSuccess(JSONObject jsonObject) {
                                dismisProgressDialog();
                                Log.d("aa", jsonObject.toString());
                                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                                    LeftFragment.list.clear();
                                    LeftFragment.list.addAll(JsonParse.getCityList(jsonObject));
                                } else {
                                    LeftFragment.list.clear();
                                }
                                LeftFragment.gridViewAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onFailed(String result) {
                                dismisProgressDialog();
                                handler.sendEmptyMessage(11);
                            }
                        };
                        volleyUtil.getJsonDataFromServer(Constant.getNodeByParentId, params, callback2);

                    }

                    @Override
                    public void onFailed(String result) {
                        //dismisProgressDialog();
                        handler.sendEmptyMessage(11);
                    }
                };
                volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);


            }

            @Override
            public void onFailed(String result) {
                //dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);

    }

    private void initTagViewPager() {
        viewPager.init(R.mipmap.tagvewpager_point01,
                R.mipmap.tagvewpager_point02, 22, 8, 2, 0);
        viewPager.setAutoNext(true, 5000);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewPager
                .getLayoutParams();
        params.width = MyApplication.getmInstance().getScreenWidth();
        params.height = (int) (params.width * 0.5);

        viewPager.setLayoutParams(params);
    }

    class OnClick implements OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()){
                case R.id.leftbutton:
                    MainActivity1.instance.toggle();
                    break;

                case R.id.btnsearch:
                    intent.setClass(getActivity(), NewsFindActivity.class);
                    startActivity(intent);
                    break;
                case R.id.news_more:
                    intent.setClass(getActivity(), NewsListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.gzq:
                    intent.setClass(getActivity(), GzqActivity.class);
                    startActivity(intent);
                    break;
                case R.id.dxs:
//                    intent.setClass(getActivity(), StudentActivity.class);
//                    startActivity(intent);

                    intent.setClass(getActivity(), EntrepreneurshipActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fgdj:
//                    FeiGongDangTuanJianActivity
//                    intent.setClass(getActivity(), FgdjActivity.class);
//                    startActivity(intent);

                    intent.setClass(getActivity(), FeiGongDangTuanJianActivity.class);
                    startActivity(intent);
                    break;

                case R.id.jzfp:
                    intent.setClass(getActivity(), jzfpActivity.class);
                    startActivity(intent);

                    break;


                case R.id.zxpx:
//                    intent.setClass(getActivity(), TrainServiceActivity.class);
//                    startActivity(intent);

                    Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();

                    break;

                case R.id.qyk:
                    intent.setClass(getActivity(), EnterpriseActivity.class);
                    startActivity(intent);

                    break;

                case R.id.jrtz:

                    //检测是否登录
                    String token = ACache.get(getContext()).getAsString("token");
                    if(token != null){
                        intent.setClass(getActivity(), FinanceServiceActivity.class);
                        startActivity(intent);
                    }else {
                        intent.putExtra("openid",1);
                        intent.setClass(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }


                    break;



                default:
                    break;
            }
        }
    }

    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("id", list.get(position).getId());
            startActivity(intent);
        }
    }

    public int calcListHeight(ListView listView, NewsListAdapter listAdapter) {
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        return totalHeight;
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
