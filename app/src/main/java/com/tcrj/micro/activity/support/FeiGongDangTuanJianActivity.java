package com.tcrj.micro.activity.support;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.newui.tagviewpager.TagViewPager;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LeftFragment;
import com.tcrj.micro.activity.MainActivity1;
import com.tcrj.micro.activity.news.FgdjActivity;
import com.tcrj.micro.activity.news.FgdjDetailActivity;
import com.tcrj.micro.activity.news.NewsDetailActivity;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;
import com.tcrj.micro.view.MyTextViewQH;
import com.tcrj.micro.view.MyTextViewXH;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeiGongDangTuanJianActivity extends BaseActivity implements View.OnClickListener {

    private MyTextViewQH qydj_more;
    private MyTextViewQH ssrw_more;
    private MyTextViewQH qyjy_more;
    private MyTextViewQH gwjy_more;
    private MyTextViewQH qyfc_more;


    private LinearLayout ll_qydj1;
    private LinearLayout ll_qydj2;
    private LinearLayout ll_qydj3;

    private LinearLayout ll_ssrw1;
    private LinearLayout ll_ssrw2;

    private LinearLayout ll_qyjy1;
    private LinearLayout ll_qyjy2;
    private LinearLayout ll_qyjy3;

    private LinearLayout ll_gwjy1;
    private LinearLayout ll_gwjy2;
    private LinearLayout ll_gwjy3;


    private MyTextViewXH qydj_title;
    private MyTextViewQH qydj_date;
    private MyTextViewXH qydj_title2;
    private MyTextViewQH qydj_date2;
    private MyTextViewXH qydj_title3;
    private MyTextViewQH qydj_date3;

    private MyTextViewXH ssrw_title1;
    private MyTextViewQH ssrw_date1;
    private MyTextViewXH ssrw_title2;
    private MyTextViewQH ssrw_date2;
    private ImageView ssrw_icon1;
    private ImageView ssrw_icon2;

    private MyTextViewXH qyjy_title1;
    private MyTextViewQH qyjy_date1;
    private MyTextViewXH qyjy_title2;
    private MyTextViewQH qyjy_date2;
    private MyTextViewXH qyjy_title3;
    private MyTextViewQH qyjy_date3;

    private MyTextViewXH gwjy_title1;
    private MyTextViewQH gwjy_date1;
    private MyTextViewXH gwjy_title2;
    private MyTextViewQH gwjy_date2;
    private MyTextViewXH gwjy_title3;
    private MyTextViewQH gwjy_date3;

    private TagViewPager viewPager;
    private static ArrayList<Object> bannerlist;
    private static ArrayList<InfoEntity> blist;

    private static ArrayList<InfoEntity> list;

    ArrayList<InfoEntity> qydjinfoList;
    ArrayList<InfoEntity> ssrwinfoList;
    ArrayList<InfoEntity> qyjyinfoList;
    ArrayList<InfoEntity>  gwjyinfoList;


    private TextView tvtitle;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fei_gong_dang_tuan_jian);

        initView();
        getData();
    }

    @Override
    public void initView() {

        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("非公党建");
        backBtn.setOnClickListener(this);

        viewPager = (TagViewPager)findViewById(R.id.mTagViewPager);

        qydj_more = findViewById(R.id.qydj_more);
        ssrw_more = findViewById(R.id.ssrw_more);
        qyjy_more = findViewById(R.id.qyjy_more);
        gwjy_more = findViewById(R.id.gwjy_more);
        qyfc_more = findViewById(R.id.qyfc_more);

        ll_qydj1 = findViewById(R.id.ll_qydj1);
        ll_qydj2 = findViewById(R.id.ll_qydj2);
        ll_qydj3 = findViewById(R.id.ll_qydj3);

        ll_ssrw1 = findViewById(R.id.ll_ssrw1);
        ll_ssrw2 = findViewById(R.id.ll_ssrw2);

        ll_qyjy1 = findViewById(R.id.ll_qyjy1);
        ll_qyjy2 = findViewById(R.id.ll_qyjy2);
        ll_qyjy3 = findViewById(R.id.ll_qyjy3);

        ll_gwjy1 = findViewById(R.id.ll_gwjy1);
        ll_gwjy2 = findViewById(R.id.ll_gwjy2);
        ll_gwjy3 = findViewById(R.id.ll_gwjy3);

        qydj_title = findViewById(R.id.qydj_title);
        qydj_date = findViewById(R.id.qydj_date);
        qydj_title2 = findViewById(R.id.qydj_title2);
        qydj_date2 = findViewById(R.id.qydj_date2);
        qydj_title3 = findViewById(R.id.qydj_title3);
        qydj_date3 = findViewById(R.id.qydj_date3);

        ssrw_title1 = findViewById(R.id.ssrw_title1);
        ssrw_date1 = findViewById(R.id.ssrw_date1);
        ssrw_title2 = findViewById(R.id.ssrw_title2);
        ssrw_date2 = findViewById(R.id.ssrw_date2);
        ssrw_icon1 = findViewById(R.id.ssrw_icon1);
        ssrw_icon2 = findViewById(R.id.ssrw_icon2);

        qyjy_title1 = findViewById(R.id.qyjy_title1);
        qyjy_date1 = findViewById(R.id.qyjy_date1);
        qyjy_title2 = findViewById(R.id.qyjy_title2);
        qyjy_date2 = findViewById(R.id.qyjy_date2);
        qyjy_title3 = findViewById(R.id.qyjy_title3);
        qyjy_date3 = findViewById(R.id.qyjy_date3);

        gwjy_title1 = findViewById(R.id.gwjy_title1);
        gwjy_date1 = findViewById(R.id.gwjy_date1);
        gwjy_title2 = findViewById(R.id.gwjy_title2);
        gwjy_date2 = findViewById(R.id.gwjy_date2);
        gwjy_title3 = findViewById(R.id.gwjy_title3);
        gwjy_date3 = findViewById(R.id.gwjy_date3);

        ll_qydj1 = findViewById(R.id.ll_qydj1);
        ll_qydj2 = findViewById(R.id.ll_qydj2);
        ll_qydj3 = findViewById(R.id.ll_qydj3);


        qydj_more.setOnClickListener(this);
        ssrw_more.setOnClickListener(this);
        qyjy_more.setOnClickListener(this);
        gwjy_more.setOnClickListener(this);
        qyfc_more.setOnClickListener(this);

        ll_qydj1.setOnClickListener(this);
        ll_qydj2.setOnClickListener(this);
        ll_qydj3.setOnClickListener(this);

        ll_qyjy1.setOnClickListener(this);
        ll_qyjy2.setOnClickListener(this);
        ll_qyjy3.setOnClickListener(this);

        ll_gwjy1.setOnClickListener(this);
        ll_gwjy2.setOnClickListener(this);
        ll_gwjy3.setOnClickListener(this);

        ll_ssrw1.setOnClickListener(this);
        ll_ssrw2.setOnClickListener(this);

        initTagViewPager();
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setOnGetView(new TagViewPager.OnGetView() {

            @Override
            public View getView(ViewGroup container, int position) {

                final int index = position;
                ImageView iv = new ImageView(FeiGongDangTuanJianActivity.this);
                RequestOptions options = new RequestOptions();
                options.centerCrop();
                Glide.with(FeiGongDangTuanJianActivity.this).load(((HashMap<String, String>) bannerlist.get(position)).get("image")).apply(options).into(iv);
                //iv.setBackgroundResource(R.mipmap.icon);
                container.addView(iv);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //System.out.println(((HashMap<String, String>) bannerlist.get(index)).get("image"));
                        Intent intent = new Intent(FeiGongDangTuanJianActivity.this, NewsDetailActivity.class);
                        intent.putExtra("id", blist.get(index).getId());
                        startActivity(intent);
                    }
                });
                return iv;
            }
        });
        bannerlist = new ArrayList<Object>();
        list = new ArrayList<InfoEntity>();

    }

    @Override
    public void getData() {
        showProgressDialog();
        getQydj();
        getSsrw();
        getQyjy();
        getGwjy();
        getQyfc();


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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {

            case R.id.btnback:
                finish();
                break;

            case R.id.qydj_more:
                intent.setClass(this, FgdjActivity.class);
                intent.putExtra("siteId","JfAJJr");
                intent.putExtra("id","ii6jYn");

                startActivity(intent);
                break;

            case R.id.ll_qydj1:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",qydjinfoList.get(0).getId());
                startActivity(intent);
                break;
            case R.id.ll_qydj2:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",qydjinfoList.get(1).getId());
                startActivity(intent);
                break;
            case R.id.ll_qydj3:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",qydjinfoList.get(2).getId());
                startActivity(intent);
                break;

            case R.id.ssrw_more:
                intent.setClass(this, FgdjActivity.class);
                intent.putExtra("siteId","JfAJJr");
                intent.putExtra("id","MJZb2y");
                startActivity(intent);
                break;

            case R.id.ll_ssrw1:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",ssrwinfoList.get(0).getId());
                startActivity(intent);
                break;
            case R.id.ll_ssrw2:
                if(ssrwinfoList.size() >= 2){
                    intent.setClass(this, FgdjDetailActivity.class);
                    intent.putExtra("id",ssrwinfoList.get(1).getId());
                    startActivity(intent);
                }

                break;

            case R.id.qyjy_more:
                intent.setClass(this, FgdjActivity.class);
                intent.putExtra("siteId","JfAJJr");
                intent.putExtra("id","JjUzAr");
                startActivity(intent);
                break;

            case R.id.ll_qyjy1:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",qyjyinfoList.get(0).getId());
                startActivity(intent);
                break;
            case R.id.ll_qyjy2:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",qyjyinfoList.get(1).getId());
                startActivity(intent);
                break;
            case R.id.ll_qyjy3:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",qyjyinfoList.get(2).getId());
                startActivity(intent);
                break;

            case R.id.gwjy_more:
                intent.setClass(this, FgdjActivity.class);
                intent.putExtra("siteId","JfAJJr");
                intent.putExtra("id","nyiquy");
                startActivity(intent);
                break;

            case R.id.ll_gwjy1:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",gwjyinfoList.get(0).getId());
                startActivity(intent);
                break;
            case R.id.ll_gwjy2:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",gwjyinfoList.get(1).getId());
                startActivity(intent);
                break;
            case R.id.ll_gwjy3:
                intent.setClass(this, FgdjDetailActivity.class);
                intent.putExtra("id",gwjyinfoList.get(2).getId());
                startActivity(intent);
                break;

            case R.id.qyfc_more:
                intent.setClass(this, FgdjActivity.class);
                intent.putExtra("siteId","JfAJJr");
                intent.putExtra("id","vymYFn");
                startActivity(intent);
                break;

            default:
                break;

        }

    }


    private void getQydj() {
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 3);
        params.put("pageindex", 1);
        params.put("siteId", "JfAJJr");
        params.put("id", "ii6jYn");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                //dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {

                    qydjinfoList = JsonParse.getInfoList(jsonObject);
                    qydj_title.setText(qydjinfoList.get(0).getTitle());
                    qydj_title2.setText(qydjinfoList.get(1).getTitle());
                    qydj_title3.setText(qydjinfoList.get(2).getTitle());

                    qydj_date.setText(DateUtil.formatToDateString(qydjinfoList.get(0).getShowTime()));
                    qydj_date2.setText(DateUtil.formatToDateString(qydjinfoList.get(1).getShowTime()));
                    qydj_date3.setText(DateUtil.formatToDateString(qydjinfoList.get(2).getShowTime()));
                } else {

                }

            }

            @Override
            public void onFailed(String result) {
                //dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);
    }

    private void getSsrw() {
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 3);
        params.put("pageindex", 1);
        params.put("siteId", "JfAJJr");
        params.put("id", "MJZb2y");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                //dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {

                    ssrwinfoList = JsonParse.getInfoList(jsonObject);
                    ssrw_title1.setText(ssrwinfoList.get(0).getTitle());
//

                    ssrw_date1.setText(DateUtil.formatToDateString(ssrwinfoList.get(0).getShowTime()));
//
                    RequestOptions options = new RequestOptions();
                    options.centerCrop();


                    if(ssrwinfoList.size() >= 2){
                        ssrw_title2.setText(ssrwinfoList.get(1).getTitle());
                        ssrw_date2.setText(DateUtil.formatToDateString(ssrwinfoList.get(1).getShowTime()));

                        if(ssrw_icon2 != null) {
                            Glide.with(FeiGongDangTuanJianActivity.this).load(ssrwinfoList.get(1).getThumbUrl()).apply(options).into(ssrw_icon2);
                        }
                    }

                    if(ssrw_icon1 != null){

                        Glide.with(FeiGongDangTuanJianActivity.this).load(ssrwinfoList.get(0).getThumbUrl()).apply(options).into(ssrw_icon1);
//                  Glide.with(FeiGongDangTuanJianActivity.this).load(infoList.get(1).getThumbUrl()).apply(options).into(ssrw_icon2);

                    }



                } else {

                }

            }

            @Override
            public void onFailed(String result) {
                //dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);
    }

    private void getQyjy() {
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 3);
        params.put("pageindex", 1);
        params.put("siteId", "JfAJJr");
        params.put("id", "JjUzAr");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                //dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                     qyjyinfoList = JsonParse.getInfoList(jsonObject);
                    qyjy_title1.setText(qyjyinfoList.get(0).getTitle());
                    qyjy_title2.setText(qyjyinfoList.get(1).getTitle());
                    qyjy_title3.setText(qyjyinfoList.get(2).getTitle());

                    qyjy_date1.setText(DateUtil.formatToDateString(qyjyinfoList.get(0).getShowTime()));
                    qyjy_date2.setText(DateUtil.formatToDateString(qyjyinfoList.get(1).getShowTime()));
                    qyjy_date3.setText(DateUtil.formatToDateString(qyjyinfoList.get(2).getShowTime()));
                } else {

                }

            }

            @Override
            public void onFailed(String result) {
                //dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);
    }

    private void getGwjy() {
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 3);
        params.put("pageindex", 1);
        params.put("siteId", "JfAJJr");
        params.put("id", "nyiquy");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                    gwjyinfoList = JsonParse.getInfoList(jsonObject);
                    gwjy_title1.setText(gwjyinfoList.get(0).getTitle());
                    gwjy_title2.setText(gwjyinfoList.get(1).getTitle());
                    gwjy_title3.setText(gwjyinfoList.get(2).getTitle());

                    gwjy_date1.setText(DateUtil.formatToDateString(gwjyinfoList.get(0).getShowTime()));
                    gwjy_date2.setText(DateUtil.formatToDateString(gwjyinfoList.get(1).getShowTime()));
                    gwjy_date3.setText(DateUtil.formatToDateString(gwjyinfoList.get(2).getShowTime()));
                } else {

                }

            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);
    }

    private void getQyfc() {
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 3);
        params.put("pageindex", 1);
        params.put("siteId", "JfAJJr");
        params.put("id", "vymYFn");

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

            }

            @Override
            public void onFailed(String result) {
                //dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);
    }
}
