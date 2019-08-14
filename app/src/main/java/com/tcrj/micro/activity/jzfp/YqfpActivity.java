package com.tcrj.micro.activity.jzfp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.adpater.DkAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.qyInfo;
import com.tcrj.micro.entity.yqfpInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class YqfpActivity extends BaseActivity implements  View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    public RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrameLayout;
    TextView txtTitle;
    ImageView btnback;
    Button btn_yq;
    String token;

    private MyOkHttp mMyOkhttp;
    private DkAdapter detailAdapter;
    private List<qyInfo.ContentBean> beanList;

    private int pageNum = 1;
    private boolean canPull = true;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dkgl);

        id = getIntent().getStringExtra("id");
        initView();
        getData();
    }

    @Override
    public void initView() {
         token = ACache.get(this).getAsString("token");
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        btn_yq = findViewById(R.id.btn_yq);
        mRecyclerView = findViewById(R.id.recycler_view);
        mPtrFrameLayout = findViewById(R.id.mPtrFrameLayout);
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);

        mPtrFrameLayout.disableWhenHorizontalMove(true);
        txtTitle.setText("邀请他人");
        btnback.setOnClickListener(this);
        btn_yq.setOnClickListener(this);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                if(!canPull){
                    return false;
                }
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                getDataFromNet(pageNum);

            }
        });
        beanList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(detailAdapter = new DkAdapter(beanList, this));
        detailAdapter.setPreLoadNumber(1);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.setEnableLoadMore(true);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        detailAdapter.setOnItemClickListener(this);
        detailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("TAG","点击重新加载数据");
                getDataFromNet(pageNum);
            }
        }, mRecyclerView);

    }

    @Override
    public void getData() {
        getDataFromNet(pageNum);
    }


    //获取网络数据
    public void getDataFromNet(final int num) {

        showProgressDialog();
        JSONObject jsonObject = new JSONObject();
        try {
        jsonObject.put("p", num+"");
        jsonObject.put("size", "10");
        jsonObject.put("name", "");

    } catch (JSONException e) {
        e.printStackTrace();
    }

        mMyOkhttp.post()
                .url(ApiConstants.yqtr_listApi)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
        @Override
        public void onFailure(int statusCode, String error_msg) {

            dismisProgressDialog();
            if(num > 1){
                loadMoreData(null,true);
            }else{
                loadData(null,true);

            }
        }

        @Override
        public void onSuccess(int statusCode, fpStringInfo response) {
            dismisProgressDialog();

            String errorCode = response.getErrorcode();
            if("9999".equals(errorCode)){
                qyInfo contentBean = new Gson().fromJson(response.getData(), qyInfo.class);

                if(num > 1){//上拉加载
                    loadMoreData(contentBean,false);
                }else{//下拉刷新
                    loadData(contentBean.getContent(),false);
                }
            }
        }
    });
}

    //上拉加载更多数据
    private void loadMoreData(qyInfo response,boolean isError) {
        Log.e("TAG","loadMoreData");
        if (response == null) {

            if(isError){
                detailAdapter.loadMoreFail();

            }else{
                detailAdapter.loadMoreEnd();
            }
        } else {
            if(pageNum > response.getTotalPages()){//没有更多数据
                detailAdapter.loadMoreEnd();
            }else{
                List<qyInfo.ContentBean> content = response.getContent();
                pageNum++;

                detailAdapter.addData(content);


                detailAdapter.loadMoreComplete();
            }

        }




    }

    //下拉刷新
    private void loadData(List<qyInfo.ContentBean> response,boolean isError) {

        if (response == null  || response.size() <= 0) {
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }

            if(isError){
//                showFaild();
            }else{
//                showEmptyView();
            }
            canPull = false;

        } else {

            canPull = true;
            pageNum++;
            detailAdapter.setNewData(response);
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }
            disableLoadMoreIfNotFullPage(mRecyclerView,response.size());
        }
    }


    public void disableLoadMoreIfNotFullPage(RecyclerView recyclerView, final int size) {
        detailAdapter.setEnableLoadMore(false);
        if (recyclerView == null) return;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager == null) return;
        if (manager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;

            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //要等到列表显示出来才可以去获取：findLastCompletelyVisibleItemPosition
                    if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != size) {
                        detailAdapter.setEnableLoadMore(true);
                    }

                    Log.e("TAG","测试："+(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1));
                }
            }, 1000);



        }
    }


    @Override
    public void onClick(View v) {



        switch (v.getId()){


            case R.id.btnback:
                finish();
                break;

            case R.id.btn_yq:

                String str = "";
                if(selectDatas.size() > 0){

                    for(int i = 0;i < selectDatas.size();i++){

                        str += selectDatas.get(i).getId()+",";
                    }

                    Log.e("TAG","邀请："+str);
                    sendMsg(str);
                }else {
                    Toast.makeText(this, "请选择邀请人员", Toast.LENGTH_SHORT).show();
                }

                break;


            default:
            break;



        }
    }

    //获取网络数据
    public void sendMsg(String str) {

        showProgressDialog();
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("token", token);
            jsonObject.put("userIds", str);
            jsonObject.put("aidpoorObjectId", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.sendyqtrApi)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(YqfpActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        dismisProgressDialog();
                        Toast.makeText(YqfpActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        String errorCode = response.getErrorcode();
                        if("9999".equals(errorCode)){
                            finish();
                        }else if("204".equals(errorCode)){
                            toLogin();
                        }
                    }
                });

    }


    private void toLogin(){
        ACache.get(this).clear();
        Intent intent = new Intent();
        intent.putExtra("openid",-2);
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);

    }


    @Override
    public void onDestroy() {
        mMyOkhttp.cancel(this);
        super.onDestroy();
    }

    private ArrayList<qyInfo.ContentBean> selectDatas = new ArrayList<>();//记录选中项
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        qyInfo.ContentBean entity = (qyInfo.ContentBean) adapter.getItem(position);

        if(!entity.isselect()){
            entity.setIsselect(true);
            selectDatas.add(entity);
        }else {
            entity.setIsselect(false);
            selectDatas.remove(entity);
        }
        adapter.notifyItemChanged(position);
    }
}
