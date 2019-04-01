package com.tcrj.micro.activity.jzfp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.CitytzAdapter;
import com.tcrj.micro.adpater.jzfptzAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.jzfptzInfo;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class CityTzInfoActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    public ImageView btnback;
    public TextView txtTitle;
    public RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrameLayout;

    private MyOkHttp mMyOkhttp;
    private CitytzAdapter detailAdapter;
    private List<jzfptzInfo.CityBean> beanList;//市

    private int pageNum = 1;
    private boolean canPull = true;

    private String id;

    private String cityId ="";
    private String countyId = "";
    private int typeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zcgsinfo);

        Bundle extras = getIntent().getExtras();
        cityId = extras.getString("cityId");
        countyId = extras.getString("countyId");
        initView();
        getData();
    }

    @Override
    public void initView() {

        id = getIntent().getStringExtra("id");
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        mRecyclerView = findViewById(R.id.recycler_view);
        mPtrFrameLayout = findViewById(R.id.mPtrFrameLayout);

        txtTitle.setText("台账信息");
        btnback.setOnClickListener(this);

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
                getData();

            }
        });
        beanList = new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(detailAdapter = new CitytzAdapter(beanList, this));
        detailAdapter.setPreLoadNumber(1);
        detailAdapter.setEnableLoadMore(false);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        detailAdapter.setOnItemClickListener(this);

//      showSuccess();

    }



    @Override
    //获取网络数据
    public void getData() {

        showProgressDialog();
        JSONObject jsonObject = new JSONObject();

        try {

            Log.e("TAG","cityId:"+cityId+" countyId:"+countyId);
            jsonObject.put("cityId", cityId);
            jsonObject.put("countyId", countyId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.sjfp_tz_listApi)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        dismisProgressDialog();
                        loadData(null,true);
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {

                        dismisProgressDialog();
                        jzfptzInfo response = new Gson().fromJson(dataBean.getData(), jzfptzInfo.class);

//                        List<jzfptzInfo.ProvinceBean> province = response.getProvince();

                        loadData(response.getCity(),false);

                    }
                });

    }


    //下拉刷新
    private void loadData(List<jzfptzInfo.CityBean> response,boolean isError) {

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

        }
    }




    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
