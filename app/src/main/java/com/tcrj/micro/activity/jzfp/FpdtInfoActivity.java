package com.tcrj.micro.activity.jzfp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.fpdtAdapter;
import com.tcrj.micro.adpater.fpdtInfoAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class FpdtInfoActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    private fpjlListInfo.ContentBean fpdtinfo;
    public RecyclerView mRecyclerView;
    private MyTextViewZH txtTitle;
    private ImageView btnback;
    private TextView fpjl;
    private TextView time;
    private TextView fpry;

    private fpdtInfoAdapter detailAdapter;
    private List<fpjlListInfo.ContentBean> beanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpdt_info);

        Bundle extras = getIntent().getExtras();
        fpdtinfo = (fpjlListInfo.ContentBean) extras.getSerializable("fpdtinfo");

        fpry = findViewById(R.id.tv_fpry);
        mRecyclerView = findViewById(R.id.recycler);
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        fpjl = findViewById(R.id.fpjl);
        time = findViewById(R.id.time);
        fpjl.setText(fpdtinfo.getAidRecord());
        time.setText("扶贫时间："+fpdtinfo.getAidTime());
        fpry.setText("扶贫人员："+fpdtinfo.getUserName());

        txtTitle.setText("扶贫动态详情");
        btnback.setOnClickListener(this);

        beanList = new ArrayList<>();
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(FpdtInfoActivity.this,2));
        mRecyclerView.setAdapter(detailAdapter = new fpdtInfoAdapter(fpdtinfo.getPicPath(), FpdtInfoActivity.this));
        detailAdapter.setPreLoadNumber(1);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.setEnableLoadMore(false);
        detailAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("fpdtinfo",fpdtinfo);
        bundle.putInt("position",position);
        toClass(this,PhototViewActivity.class,bundle);
    }


    @Override
    public void initView() {

    }

    @Override
    public void getData() {

    }
}
