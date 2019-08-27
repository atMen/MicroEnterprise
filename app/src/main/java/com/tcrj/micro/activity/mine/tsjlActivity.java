package com.tcrj.micro.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.activity.zxzp.TdjlInfoActivity;
import com.tcrj.micro.adpater.tdjlAdapter;
import com.tcrj.micro.adpater.tsjlAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.tdjlInfo;
import com.tcrj.micro.entity.tsjlInfo;
import com.tcrj.micro.jpush.PushMessageActivity;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class tsjlActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    MyTextViewZH txtTitle;
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrameLayout;
    RelativeLayout rl_loding;
    ImageView btnback;
    LinearLayout content;
    TextView tv_empty;

    private MyOkHttp mMyOkhttp;
    private tsjlAdapter detailAdapter;
    private boolean canPull = true;
    private int pageNum = 1;
    private List<tsjlInfo.ContentBean> beanList;

    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdjl);
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        mobile = ACache.get(this).getAsString("mobile");

        initView();
        getData();
    }

    @Override
    public void initView() {

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("小微库统计");

        mPtrFrameLayout = findViewById(R.id.mPtrFrameLayout);
        mRecyclerView = findViewById(R.id.recycler_view);
        tv_empty = findViewById(R.id.tv_empty);
        rl_loding = findViewById(R.id.rl_loding);
        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mPtrFrameLayout.disableWhenHorizontalMove(true);
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
                rl_loding.setVisibility(View.GONE);
                mPtrFrameLayout.setVisibility(View.VISIBLE);
                pageNum = 1;

                getDataFromNet(pageNum);

            }
        });
        beanList = new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(tsjlActivity.this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(detailAdapter = new tsjlAdapter(beanList, tsjlActivity.this));
//      detailAdapter.setPreLoadNumber(1);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.setEnableLoadMore(true);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
      detailAdapter.setOnItemClickListener(this);

        detailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("TAG","点击重新加载数据");
                rl_loding.setVisibility(View.GONE);
                mPtrFrameLayout.setVisibility(View.VISIBLE);
                getDataFromNet(pageNum);
            }
        }, mRecyclerView);

    }

    @Override
    public void getData() {
        getDataFromNet(1);
    }

    //获取网络数据
    private void getDataFromNet(final int num) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", mobile);
            jsonObject.put("p", num+"");
            jsonObject.put("size", 20);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.tsjllist_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

//                      rl_loding.setVisibility(View.GONE);

                        if(num > 1){
                            loadMoreData(null,true);
                        }else{
                            loadData(null,true);
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo data) {
                        rl_loding.setVisibility(View.GONE);

                        if("9999".equals(data.getErrorcode())) {

                            tsjlInfo response = new Gson().fromJson(data.getData(), tsjlInfo.class);

                            if(num > 1){//上拉加载
                                loadMoreData(response,false);
                            }else{//下拉刷新
                                loadData(response,false);
                            }
                        }else if("204".equals(data.getErrorcode())){
                            finish();
                            toClass(tsjlActivity.this, LoginActivity.class,null);
                        }
                    }
                });
    }


    //上拉加载更多数据
    private void loadMoreData(tsjlInfo response, boolean isError) {
        Log.e("TAG","loadMoreData");

        if (response.getContent() == null) {
            if(isError){
                detailAdapter.loadMoreFail();
            }else{
                detailAdapter.loadMoreEnd();
            }

        } else {
            List<tsjlInfo.ContentBean> result = response.getContent();
            if(result == null || result.size() == 0){//没有更多数据
                detailAdapter.loadMoreEnd();
            }else{
                pageNum++;
                detailAdapter.addData(result);
                detailAdapter.loadMoreComplete();
            }

        }

    }

    //下拉刷新
    private void loadData(tsjlInfo response,boolean isError) {

        if (response == null || response.getContent().size() == 0) {
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }
            if(isError){
                Toast.makeText(this, "刷新失败", Toast.LENGTH_SHORT).show();
            }else{
                EmptyView();
            }
            canPull = false;

        } else {

            canPull = true;
            pageNum++;

            if(mRecyclerView != null){
                mRecyclerView.scrollToPosition(0);
            }
            detailAdapter.setNewData(response.getContent());
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }

            Success();
            disableLoadMoreIfNotFullPage(mRecyclerView,response.getContent().size());
        }
    }

    private void Success() {
        mPtrFrameLayout.setVisibility(View.VISIBLE);
        tv_empty.setVisibility(View.GONE);
    }

    private void EmptyView() {
        mPtrFrameLayout.setVisibility(View.GONE);
        tv_empty.setVisibility(View.VISIBLE);
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
                        Log.e("TAG","setEnableLoadMore(true)");
                    }

                    Log.e("TAG","测试："+(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1));
                }
            }, 1000);


        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        tsjlInfo.ContentBean item = (tsjlInfo.ContentBean) adapter.getItem(position);

        Intent pushIntent = new Intent();
        pushIntent.setClass(this, PushMessageActivity.class);
        pushIntent.putExtra("messageContent", item.getMessage());
        startActivity(pushIntent);
    }
}
