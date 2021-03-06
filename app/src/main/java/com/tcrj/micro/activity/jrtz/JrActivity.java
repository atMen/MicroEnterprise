package com.tcrj.micro.activity.jrtz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.VolleyUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.NetJSCallJavaActivity;
import com.tcrj.micro.activity.NewsFragment;
import com.tcrj.micro.activity.left.LeftDetailActivity;
import com.tcrj.micro.activity.left.NewLeftListActivity;
import com.tcrj.micro.activity.news.FgdjDetailActivity;
import com.tcrj.micro.adpater.Fgdj2Adapter;
import com.tcrj.micro.adpater.Fgdj3Adapter;
import com.tcrj.micro.adpater.FgdjAdapter;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.adpater.jrtzAdapter;
import com.tcrj.micro.adpater.jrtzcwAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.bankInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.until.ACache;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;

public class JrActivity extends BaseActivity {

    private TextView tvtitle;
    private ImageView backBtn;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private ImageView iv_jr;

    private LinearLayout llzscq;
    private LinearLayout llhyrd;
    private LinearLayout llzcjd;
    private TextView tvzscq;
    private TextView tvhyrd;
    private TextView tvzcjd;
    private View zscq;
    private View hyrd;
    private View zcjd;
    private LinearLayout ll_one;
    private LinearLayout ll_two;
    private LinearLayout ll_three;



    private static ArrayList<bankInfo> list;
    private int pageIndex = 1;

    private jrtzAdapter detailAdapter;
    private jrtzcwAdapter detailAdapter2;
    private MyOkHttp mMyOkhttp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jr);
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        initView();
        getData();
    }

    @Override
    public void initView() {

        ll_one = (LinearLayout) findViewById(R.id.ll_one);
        ll_two = (LinearLayout) findViewById(R.id.ll_two);
        ll_three = (LinearLayout) findViewById(R.id.ll_three);
        llzscq = (LinearLayout) findViewById(R.id.ll_zscq);
        llhyrd = (LinearLayout) findViewById(R.id.ll_hyrd);
        llzcjd = (LinearLayout) findViewById(R.id.ll_zcjd);
        tvzscq = (TextView) findViewById(R.id.tv_zscq);
        tvhyrd = (TextView) findViewById(R.id.tv_hyrd);
        tvzcjd = (TextView) findViewById(R.id.tv_zcjd);
        zscq = (View) findViewById(R.id.zscq);
        hyrd = (View) findViewById(R.id.hyrd);
        zcjd = (View) findViewById(R.id.zcjd);

        iv_jr = findViewById(R.id.iv_jr);
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("金融投资");
        mRecyclerView = (RecyclerView) findViewById(R.id.list1);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.list2);

        list = new ArrayList<bankInfo>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(detailAdapter = new jrtzAdapter(list));

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView2.setAdapter(detailAdapter2 = new jrtzcwAdapter(list));

        detailAdapter.setEnableLoadMore(false);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        detailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                bankInfo item = (bankInfo) adapter.getItem(position);

                ACache.get(JrActivity.this).put("bankid",item.getId());
                Intent intent = new Intent(JrActivity.this,jrtzActivity.class);
                startActivity(intent);
            }
        });

        detailAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                bankInfo item = (bankInfo) adapter.getItem(position);
                ACache.get(JrActivity.this).put("bankid",item.getId());
                Intent intent = new Intent(JrActivity.this,jrtzActivity.class);
                startActivity(intent);
            }
        });

        iv_jr.setOnClickListener(new OnClick());
        backBtn.setOnClickListener(new OnClick());
        llhyrd.setOnClickListener(new OnClick());
        llzcjd.setOnClickListener(new OnClick());
        llzscq.setOnClickListener(new OnClick());
    }

    @Override
    public void getData() {
        showProgressDialog();
        getListInfo1();
    }

    private void getListInfo1(){


        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("p", "1");
            jsonObject.put("size", "15");
            jsonObject.put("type", "11501");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.jrztbanklist_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        Toast.makeText(JrActivity.this, error_msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        String errorCode = dataBean.getErrorcode();
                        if("9999".equals(errorCode)){

                            List<bankInfo> response = new Gson().fromJson(dataBean.getData(), new TypeToken<List<bankInfo>>() {}.getType());

                            detailAdapter.setNewData(response);

                            getListInfo2();

                        }
                    }
                });



    }

    private void getListInfo2(){

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("p", "1");
            jsonObject.put("size", "15");
            jsonObject.put("type", "11502");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.jrztbanklist_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(JrActivity.this, error_msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        dismisProgressDialog();
                        String errorCode = dataBean.getErrorcode();
                        if("9999".equals(errorCode)){


                            List<bankInfo> response = new Gson().fromJson(dataBean.getData(), new TypeToken<List<bankInfo>>() {}.getType());

                            detailAdapter2.setNewData(response);

                        }
                    }
                });
    }

    class OnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(JrActivity.this, NewLeftListActivity.class);

            switch (v.getId()) {
                case R.id.btnback:
                    finish();
                    break;

                case R.id.iv_jr:
                    toClass(JrActivity.this,NetJSCallJavaActivity.class,null);
                    break;

                case R.id.ll_hyrd:
                    hyrd.setVisibility(View.VISIBLE);
                    tvhyrd.setTextColor(getResources().getColor(R.color.app_blue));

                    zscq.setVisibility(View.GONE);
                    tvzcjd.setTextColor(getResources().getColor(R.color.black));

                    zcjd.setVisibility(View.GONE);
                    tvzcjd.setTextColor(getResources().getColor(R.color.black));

                    ll_one.setVisibility(View.VISIBLE);
                    ll_two.setVisibility(View.GONE);
                    ll_three.setVisibility(View.GONE);

                    break;

                case R.id.ll_zcjd:

                    hyrd.setVisibility(View.GONE);
                    tvhyrd.setTextColor(getResources().getColor(R.color.black));

                    zcjd.setVisibility(View.VISIBLE);
                    tvzcjd.setTextColor(getResources().getColor(R.color.app_blue));

                    zscq.setVisibility(View.GONE);
                    tvzscq.setTextColor(getResources().getColor(R.color.black));

                    ll_one.setVisibility(View.GONE);
                    ll_two.setVisibility(View.VISIBLE);
                    ll_three.setVisibility(View.GONE);

                    break;

                case R.id.ll_zscq:

                    hyrd.setVisibility(View.GONE);
                    tvhyrd.setTextColor(getResources().getColor(R.color.black));

                    zcjd.setVisibility(View.GONE);
                    tvzcjd.setTextColor(getResources().getColor(R.color.black));

                    zscq.setVisibility(View.VISIBLE);
                    tvzscq.setTextColor(getResources().getColor(R.color.app_blue));

                    ll_one.setVisibility(View.GONE);
                    ll_two.setVisibility(View.GONE);
                    ll_three.setVisibility(View.VISIBLE);

                    break;

                default:
                    break;
            }
        }
    }

}
