package com.tcrj.micro.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.newui.hzwlistview.xlist.XListView;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;
import com.tcrj.micro.view.MyTextViewQH;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;

public class FgdjActivity extends BaseActivity {

    private TextView tvtitle;
    private ImageView backBtn;
    private XListView list_news;
    private static ArrayList<InfoEntity> list;
    private NewsListAdapter adapter;
    private int pageIndex = 1;

    private String siteId;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fgdj);

        siteId = getIntent().getStringExtra("siteId");
        id = getIntent().getStringExtra("id");

        Log.e("TAG","siteId:"+siteId+"  id:"+id);

        initView();
        getData();
    }

    @Override
    public void initView() {
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("非公党建");
        list_news = (XListView) findViewById(R.id.listview);


        list_news.setPullLoadEnable(true);
        list_news.setXListViewListener(new XListView.IXListViewListener() {

            @Override
            public void onRefresh() {
                list_news.setRefreshTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
                pageIndex = 1;
                getFreshVolley(pageIndex);
            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                getLoadVolley(pageIndex);
            }
        });

        list = new ArrayList<InfoEntity>();
        adapter = new NewsListAdapter(this, list);
        list_news.setAdapter(adapter);
        list_news.setOnItemClickListener(new OnItemClick());
        backBtn.setOnClickListener(new OnClick());

    }

    @Override
    public void getData() {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex);

        params.put("siteId", siteId);
        params.put("id", id);



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
                        list_news.setPullLoadEnable(false);
                    }else{
                        list_news.setPullLoadEnable(true);
                    }
                }else{
                    list.clear();
                    list_news.setPullLoadEnable(false);
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
        params.put("siteId", siteId);
        params.put("id", id);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.clear();
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        list_news.setPullLoadEnable(false);
                    }else{
                        list_news.setPullLoadEnable(true);
                    }
                }else{
                    list_news.setPullLoadEnable(false);
                    list.clear();

                }
                list_news.stopRefresh();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String result) {
                list_news.stopRefresh();
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
        params.put("siteId", siteId);
        params.put("id", id);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        list_news.setPullLoadEnable(false);
                    }else{
                        list_news.setPullLoadEnable(true);
                    }
                }else{
                    list_news.setPullLoadEnable(false);
                }
                list_news.setSelection(list.size() - 1);
                list_news.stopLoadMore();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String result) {
                list_news.stopLoadMore();
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);

    }

    class OnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btnback:
                    finish();
                    break;
            }
        }
    }

    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(FgdjActivity.this, FgdjDetailActivity.class);
            intent.putExtra("id", list.get(position-1).getId());
            startActivity(intent);
        }
    }

}
