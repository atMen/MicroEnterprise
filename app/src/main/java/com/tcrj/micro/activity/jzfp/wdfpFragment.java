package com.tcrj.micro.activity.jzfp;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.spring.chanba.ui.home.FinanceServiceActivity;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.activity.base.BaseFragment;
import com.tcrj.micro.adpater.wdfpAdapter;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.fpdxInfo;
import com.tcrj.micro.entity.qyListInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by leict on 2018/6/28.
 */

public class wdfpFragment extends BaseFragment implements  wdfpAdapter.OnPlayClickListener {

    public RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrameLayout;

    private MyOkHttp mMyOkhttp;
    private wdfpAdapter detailAdapter;
    private List<fpDataInfo.ContentBean> beanList;

    private int pageNum = 1;
    private boolean canPull = true;

    @Override
    protected int setLayout() {
        return R.layout.dy_fregment;
    }

    @Override
    protected void setView() {
        EventBus.getDefault().register(this);

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mPtrFrameLayout = mRootView.findViewById(R.id.mPtrFrameLayout);
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
                pageNum = 1;
              getData(pageNum);

            }
        });
        beanList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(detailAdapter = new wdfpAdapter(beanList, mContext));
        detailAdapter.setPreLoadNumber(1);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.setEnableLoadMore(true);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        detailAdapter.setOnPlayClickListener(this);
        detailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("TAG","点击重新加载数据");
                getData(pageNum);
            }
        }, mRecyclerView);


    }

    @Override
    protected void setData() {
        getData(pageNum);
    }

    //获取网络数据
    private void getData(final int num) {

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("p", num+"");
            jsonObject.put("size", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.fpry_listApi)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();

                        if(num > 1){
                            loadMoreData(null,true);
                        }else{
                            loadData(null,true);
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        dismisProgressDialog();
                        String errorCode = dataBean.getErrorcode();
                        if("9999".equals(errorCode)){

                            fpDataInfo response = new Gson().fromJson(dataBean.getData(), fpDataInfo.class);

                            Log.e("TAG","dataBean.getData():"+response.getTotalElements());
                            if(num > 1){//上拉加载
                                loadMoreData(response,false);
                            }else{//下拉刷新
                                loadData(response.getContent(),false);
                            }
                        }
                    }
                });

    }

    //上拉加载更多数据
    private void loadMoreData(fpDataInfo response,boolean isError) {
        Log.e("TAG","loadMoreData");
        if (response == null) {
            Log.e("TAG","response == null:");
            if(isError){
                detailAdapter.loadMoreFail();

            }else{
                detailAdapter.loadMoreFail();
            }

        } else {

            if(pageNum > response.getTotalPages()){//没有更多数据
                Log.e("TAG","没有更多数据"+response.getTotalPages());
                detailAdapter.loadMoreEnd();
            }else{
                List<fpDataInfo.ContentBean> content = response.getContent();
                Log.e("TAG","content:"+content.size());
                pageNum++;
                detailAdapter.addData(content);
                detailAdapter.loadMoreComplete();
            }
        }
    }

    //下拉刷新
    private void loadData(List<fpDataInfo.ContentBean> response,boolean isError) {

        if (response == null  || response.size() <= 0) {
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }
            if(isError){

            }else{
                Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
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
    public void onPlayItemClick(String id, int position) {
        Intent intent = new Intent();
        //检测是否登录
        String token = ACache.get(mContext).getAsString("token");
        if(token != null){

            if(position == 0){
                //我要扶贫
                intent.putExtra("token",token);
                intent.putExtra("id",id);
                intent.setClass(mContext, WyfpActivity.class);
                mContext.startActivity(intent);
            }else {
                //邀请他人
                intent.setClass(mContext, YqfpActivity.class);
                mContext.startActivity(intent);
            }

        }else {
            intent.putExtra("openid",-2);
            intent.setClass(mContext, LoginActivity.class);
            mContext.startActivity(intent);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Log.e("TAG","Event123 ");
        switch (messageEvent.getType()){

            case 002:
                Log.e("TAG","请求新数据123");
//              getData(1);
                break;


            default:
                break;
        }

    }
}
