package com.tcrj.micro.activity.left;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.newui.hzwlistview.xlist.XListView;
import com.newui.hzwlistview.xlist.XListView.IXListViewListener;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.LeftListAdapter;
import com.tcrj.micro.adpater.NewLeftListAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.leftListInfo;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.OnClickListener;
import static android.widget.AdapterView.OnItemClickListener;

public class NewLeftListActivity extends BaseActivity {

    private String cityid;
    private String id;
    private String title;
    private TextView tvtitle;
    private ImageView backBtn;
    private ImageView btnsearch;
    private XListView listview;
    private ArrayList<leftListInfo> list = new ArrayList<leftListInfo>();
    public static NewLeftListAdapter adapter;
    private int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_list);
        initView();
        getData();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();

        cityid = intent.getStringExtra("cityId");
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        btnsearch = (ImageView) findViewById(R.id.btnsearch);
        backBtn.setVisibility(View.VISIBLE);
        btnsearch.setVisibility(View.VISIBLE);
        tvtitle.setText(title);
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
        adapter = new NewLeftListAdapter(this,list);
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
        params.put("pagesize", "10");
        params.put("pageindex", pageIndex);
//      params.put("siteId", "JfAJJr");
        if(cityid != null){
            params.put("id", cityid);
        }else {
            params.put("id", id);
        }


        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aaa", jsonObject.toString());
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    list.clear();
                    ArrayList<leftListInfo> arraylist = JsonParse.getLeftInfoList(jsonObject);
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
//        params.put("siteId", "JfAJJr");
        if(cityid != null){
            params.put("id", cityid);
        }else {
            params.put("id", id);
        }

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<leftListInfo> arraylist = JsonParse.getLeftInfoList(jsonObject);
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
//      params.put("siteId", "JfAJJr");
        if(cityid != null){
            params.put("id", cityid);
        }else {
            params.put("id", id);
        }

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<leftListInfo> arraylist = JsonParse.getLeftInfoList(jsonObject);
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
        public void onItemClick(AdapterView<?> parent, View view, int position, long lid) {

            Intent intent = new Intent(NewLeftListActivity.this,LeftDetailActivity.class);
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
                    intent.setClass(NewLeftListActivity.this, LeftFindActivity.class);
                    startActivity(intent);
                    break;

                default:
                    break;
            }
        }
    }
}
