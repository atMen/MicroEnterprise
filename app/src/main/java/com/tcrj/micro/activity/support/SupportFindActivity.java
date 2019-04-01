package com.tcrj.micro.activity.support;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.newui.clearedittext.ClearEditText;
import com.newui.hzwlistview.xlist.XListView;
import com.newui.hzwlistview.xlist.XListView.IXListViewListener;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.news.NewsDetailActivity;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.adpater.SearchListAdapter;
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

public class SupportFindActivity extends BaseActivity {

    private ImageView backBtn;
    private TextView search_logo;
    private XListView listview;
    private ArrayList<InfoEntity> list = new ArrayList<InfoEntity>();
    public static SearchListAdapter adapter;
    private ClearEditText filter_edit;
    private int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_find);
        initView();
        //getData();
    }

    @Override
    public void initView() {
        backBtn = (ImageView) findViewById(R.id.btnback);
        search_logo = (TextView) findViewById(R.id.search_logo);
        filter_edit = (ClearEditText) findViewById(R.id.filter_edit);
        backBtn.setVisibility(View.VISIBLE);
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
        adapter = new SearchListAdapter(this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClick());
        backBtn.setOnClickListener(new OnClick());
        filter_edit.setOnEditorActionListener(new OnEditor());
    }



    @Override
    public void getData() {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex);
        params.put("siteId", "JfAJJr");
        params.put("key", filter_edit.getText().toString());

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
        volleyUtil.getJsonDataFromServer(Constant.searchAll, params, callback2);

    }

    // 刷新
    public void getFreshVolley(int pageNo) {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex);
        params.put("siteId", "JfAJJr");
        params.put("key", filter_edit.getText().toString());

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
        volleyUtil.getJsonDataFromServer(Constant.searchAll, params, callback2);

    }

    // 加载
    public void getLoadVolley(int pageNo) {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex);
        params.put("siteId", "JfAJJr");
        params.put("key", filter_edit.getText().toString());

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
        volleyUtil.getJsonDataFromServer(Constant.searchAll, params, callback2);

    }

    class OnItemClick implements  OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SupportFindActivity.this,SupportDetailActivity.class);
            intent.putExtra("id",list.get(position-1).getId());
            startActivity(intent);
        }
    }

    class OnEditor implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                filter_edit.requestFocus();
                search_logo.setVisibility(View.GONE);
                listview.setVisibility(View.VISIBLE);
                getData();
                return true;
            } else {
                return false;
            }
        }
    }

    class OnClick implements  OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnback:
                    finish();
                    break;
            }
        }
    }
}
