package com.tcrj.micro.activity.news;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.android.volley.toolbox.VolleyUtil;
import com.newui.animation.BaseAnimatorSet;
import com.newui.animation.SlideEnter.SlideRightEnter;
import com.newui.animation.SlideExit.SlideRightExit;
import com.newui.clearedittext.ClearEditText;
import com.newui.hzwlistview.xlist.XListView;
import com.newui.hzwlistview.xlist.XListView.IXListViewListener;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;
import static com.newui.waterlistview.WaterDropListView.OnItemClickListener;

public class NewsListActivity extends BaseActivity {

    private TextView tvtitle;
    private ImageView backBtn;
    private ImageView btnsearch;
    private XListView listview;
    private ArrayList<InfoEntity> list = new ArrayList<InfoEntity>();
    public static NewsListAdapter adapter;
    private int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initView();
        getData();
    }

    @Override
    public void initView() {
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        btnsearch = (ImageView) findViewById(R.id.btnsearch);
        backBtn.setVisibility(View.VISIBLE);
        btnsearch.setVisibility(View.VISIBLE);
        tvtitle.setText("要闻");
        listview = (XListView) findViewById(R.id.listview);
        listview.setPullLoadEnable(true);
        listview.setXListViewListener(new IXListViewListener() {

            @Override
            public void onRefresh() {
                listview.setRefreshTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
                pageIndex = 1;
                getFreshVolley(pageIndex);
            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                getLoadVolley(pageIndex);
            }
        });
        adapter = new NewsListAdapter(this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClick());
        backBtn.setOnClickListener(new OnClick());
        btnsearch.setOnClickListener(new OnClick());
    }



    @Override
    public void getData() {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex);
        params.put("siteId", "JfAJJr");
        params.put("id", "FJb6Jr");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aaa", jsonObject.toString());
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    list.clear();
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        listview.setPullLoadEnable(false);
                    }else{
                        listview.setPullLoadEnable(true);
                    }
                }else{
                    list.clear();
                    listview.setPullLoadEnable(false);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);

    }

    // 刷新
    public void getFreshVolley(int pageNo) {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex );
        params.put("siteId", "JfAJJr");
        params.put("id", "FJb6Jr");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.clear();
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        listview.setPullLoadEnable(false);
                    }else{
                        listview.setPullLoadEnable(true);
                    }
                }else{
                    listview.setPullLoadEnable(false);
                    list.clear();

                }
                listview.stopRefresh();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String result) {
                listview.stopRefresh();
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);

    }

    // 加载
    public void getLoadVolley(int pageNo) {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex);
        params.put("siteId", "JfAJJr");
        params.put("id", "FJb6Jr");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        listview.setPullLoadEnable(false);
                    }else{
                        listview.setPullLoadEnable(true);
                    }
                }else{
                    listview.setPullLoadEnable(false);
                }
                listview.setSelection(list.size() - 1);
                listview.stopLoadMore();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String result) {
                listview.stopLoadMore();
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);

    }

    class OnItemClick implements  OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(NewsListActivity.this,NewsDetailActivity.class);
            intent.putExtra("id",list.get(position-1).getId());
            startActivity(intent);
        }
    }



    class OnClick implements  OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnback:
                    finish();
                    break;
                case R.id.btnsearch:
                    Intent intent = new Intent();
                    intent.setClass(NewsListActivity.this, NewsFindActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
