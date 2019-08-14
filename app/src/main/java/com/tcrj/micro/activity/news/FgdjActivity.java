package com.tcrj.micro.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.VolleyUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.newui.hzwlistview.xlist.XListView;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.left.LeftDetailActivity;
import com.tcrj.micro.activity.left.NewLeftListActivity;
import com.tcrj.micro.adpater.Fgdj2Adapter;
import com.tcrj.micro.adpater.Fgdj3Adapter;
import com.tcrj.micro.adpater.FgdjAdapter;
import com.tcrj.micro.adpater.JybjAdapter;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.adpater.gzjlAdapter;
import com.tcrj.micro.adpater.qzyxAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.qyzpListInfo;
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
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private RecyclerView mRecyclerView3;
    private TextView tv_more1;
    private TextView tv_more2;
    private TextView tv_more3;

    private static ArrayList<InfoEntity> list;
    private NewsListAdapter adapter;
    private int pageIndex = 1;

    private FgdjAdapter detailAdapter;
    private Fgdj2Adapter detailAdapter2;
    private Fgdj3Adapter detailAdapter3;
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


        tv_more1 = (TextView) findViewById(R.id.tv_more1);
        tv_more2 = (TextView) findViewById(R.id.tv_more2);
        tv_more3 = (TextView) findViewById(R.id.tv_more3);

        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("非公党建");
        mRecyclerView = (RecyclerView) findViewById(R.id.list1);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.list2);
        mRecyclerView3 = (RecyclerView) findViewById(R.id.list3);


        list = new ArrayList<InfoEntity>();
//        adapter = new NewsListAdapter(this, list);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(detailAdapter = new FgdjAdapter(list));

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView2.setAdapter(detailAdapter2 = new Fgdj2Adapter(list));

        mRecyclerView3.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView3.setNestedScrollingEnabled(false);
        mRecyclerView3.setAdapter(detailAdapter3 = new Fgdj3Adapter(list));

        detailAdapter.setEnableLoadMore(false);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        detailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InfoEntity item = (InfoEntity) adapter.getItem(position);

                Intent intent = new Intent(FgdjActivity.this,LeftDetailActivity.class);
                intent.putExtra("id",item.getId());
                startActivity(intent);
            }
        });

        detailAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InfoEntity item = (InfoEntity) adapter.getItem(position);

                Intent intent = new Intent(FgdjActivity.this,LeftDetailActivity.class);
                intent.putExtra("id",item.getId());
                startActivity(intent);
            }
        });

        detailAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InfoEntity item = (InfoEntity) adapter.getItem(position);

                Intent intent = new Intent(FgdjActivity.this,LeftDetailActivity.class);
                intent.putExtra("id",item.getId());
                startActivity(intent);
            }
        });



        backBtn.setOnClickListener(new OnClick());
        tv_more1.setOnClickListener(new OnClick());
        tv_more2.setOnClickListener(new OnClick());
        tv_more3.setOnClickListener(new OnClick());
    }

    @Override
    public void getData() {
        showProgressDialog();
        getListInfo1();

    }

    private void getListInfo1(){

        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 5);
        params.put("pageindex", pageIndex);
        params.put("id", "2uMfEf");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {

                Log.d("aaa", jsonObject.toString());
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    list.clear();
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.addAll(arraylist);
                    detailAdapter.setNewData(arraylist);
                }else{
                    list.clear();

                }

                getListInfo2();

            }

            @Override
            public void onFailed(String result) {

                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);

    }

    private void getListInfo2(){

        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 5);
        params.put("pageindex", pageIndex);
        params.put("id", "ZBze2q");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {

                Log.d("aaa", jsonObject.toString());
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    list.clear();
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.addAll(arraylist);
                    detailAdapter2.setNewData(arraylist);
                }else{
                    list.clear();

                }
                getListInfo3();

//                detailAdapter.setNewData(list);
//                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String result) {

                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoList, params, callback2);

    }

    private void getListInfo3(){

        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 5);
        params.put("pageindex", pageIndex);
        params.put("id", "EBjQve");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aaa", jsonObject.toString());
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    list.clear();
                    ArrayList<InfoEntity> arraylist = JsonParse.getInfoList(jsonObject);
                    list.addAll(arraylist);
                    detailAdapter3.setNewData(arraylist);
                }else{
                    list.clear();

                }
//                detailAdapter.setNewData(list);

//                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String result) {
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
            intent.setClass(FgdjActivity.this, NewLeftListActivity.class);

            switch (v.getId()) {
                case R.id.btnback:
                    finish();
                    break;

                case R.id.tv_more1:

                    intent.putExtra("id", "2uMfEf");
                    intent.putExtra("title", "共青妇资讯");
                    startActivity(intent);

                    break;

                case R.id.tv_more2:

                    intent.putExtra("id", "ZBze2q");
                    intent.putExtra("title", "综合公告");
                    startActivity(intent);
                    break;

                case R.id.tv_more3:
                    intent.putExtra("id", "EBjQve");
                    intent.putExtra("title", "诚信文明");
                    startActivity(intent);
                    break;

                default:
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
