package com.tcrj.micro.activity.jzfp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.activity.base.BaseFragment;
import com.tcrj.micro.activity.zxzp.grzpActivity;
import com.tcrj.micro.adpater.ListDropDownAdapter;
import com.tcrj.micro.adpater.fpdxAdapter;
import com.tcrj.micro.adpater.wdfpAdapter;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.fpdxInfo;

import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.entity.qyListInfo;
import com.tcrj.micro.entity.zcgsInfo;
import com.tcrj.micro.entity.zwInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;



/**
 * Created by leict on 2018/4/19.
 */

public class fpdxFregment extends BaseFragment implements fpdxAdapter.OnPlayClickListener {

    public RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrameLayout;

    private MyOkHttp mMyOkhttp;
    private fpdxAdapter detailAdapter;
    private List<fpjlListInfo.ContentBean> beanList;

    private int pageNum = 1;
    private boolean canPull = true;
    private String token;

    @Override
    protected int setLayout() {
        return R.layout.dy_fregment;
    }

    @Override
    protected void setView() {
        EventBus.getDefault().register(this);
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        token = ACache.get(mContext).getAsString("token");

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
        mRecyclerView.setAdapter(detailAdapter = new fpdxAdapter(beanList, mContext));

        detailAdapter.setEnableLoadMore(false);

        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        detailAdapter.setOnPlayClickListener(this);

    }


    @Override
    protected void setData() {
        getData(pageNum);
    }


    //获取网络数据
    private void getData(final int num) {
        showProgressDialog("正在加载...");
        JSONObject jsonObject = new JSONObject();

        try {

            //TODO:注意修改此处token
            jsonObject.put("token", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.fpjl_listApi)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();

                        dismisProgressDialog();
                        loadData(null,true);

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {

                        dismisProgressDialog();

                        String fpjlListInfos = response.getData();

                        List<fpjlListInfo.ContentBean> zcgsInfos = parseNoHeaderJArray(fpjlListInfos);

                        loadData(zcgsInfos,false);
                    }
                });

    }



    //下拉刷新
    private void loadData(List<fpjlListInfo.ContentBean> response,boolean isError) {

        if (response == null  || response.size() <= 0) {
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }
            detailAdapter.setNewData(response);
            if(isError){

            }else{

            }
            canPull = false;

        } else {

            canPull = true;
            pageNum++;
            detailAdapter.setNewData(response);
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }
//            disableLoadMoreIfNotFullPage(mRecyclerView,response.size());
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
    public void onPlayItemClick(fpjlListInfo.ContentBean item, int position) {

        //检测是否登录
        String token = ACache.get(mContext).getAsString("token");
        if(token != null){

            if(position == 0){

                Bundle bundle = new Bundle();
                bundle.putSerializable("fpxxKey",item);
                toClass(mContext,WyfpActivity.class,bundle);

            }else {
                //取消扶贫
                showNormalDialog(item.getId());
            }

        }else {
//
        }
    }



    private void showNormalDialog(final String id){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());

        normalDialog.setTitle("取消扶贫");
        normalDialog.setMessage("确定取消扶贫?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        qxfp(id);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void qxfp(String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("aidPoorRecordId", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.qxfp_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {

                        dismisProgressDialog();
                        String errorCode = null;

                        try {
                             errorCode = response.getString("errorcode");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if("9999".equals(errorCode)){
                            getData(1);
                        }

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Log.e("TAG","Event ");
        switch (messageEvent.getType()){

            case 002:
                Log.e("TAG","请求新数据");
                getData(1);
                break;


            default:
                break;
        }

    }


    private List<fpjlListInfo.ContentBean> parseNoHeaderJArray(String strByJson) {

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();

        Gson gson = new Gson();
        List<fpjlListInfo.ContentBean> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            fpjlListInfo.ContentBean userBean = gson.fromJson(user, fpjlListInfo.ContentBean.class);
            userBeanList.add(userBean);
        }
        return userBeanList;
    }

}
