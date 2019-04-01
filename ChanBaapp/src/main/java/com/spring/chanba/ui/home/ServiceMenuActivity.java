package com.spring.chanba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.ServiceMenuAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.contract.ServiceMenuContract;
import com.spring.chanba.presenter.ServiceMenuPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.xrecycler.ProgressStyle;
import com.spring.chanba.xrecycler.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 服务菜单列表
 */
public class ServiceMenuActivity extends BaseActivity implements ServiceMenuContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private XRecyclerView recyclerView;
    private ServiceMenuAdapter adapter;
    private List<ServiceMenuEntity.DataBean> dataList;
    private ServiceMenuContract.Presenter presenter;
    private int pageNumber = 1;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initView();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        type = getIntent().getStringExtra("type");
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        recyclerView = (XRecyclerView) findViewById(R.id.x_recyclerview);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleHead.setText("资质列表");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        //recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerView.getDefaultFootView().setLoadingHint("努力加载中...");
        recyclerView.getDefaultFootView().setNoMoreHint("加载完成");
        recyclerView.setLimitNumberToCallLoadMore(2);
        dataList = new ArrayList<>();
        adapter = new ServiceMenuAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ServiceMenuAdapter.OnItemCallBack() {
            @Override
            public void setOnClickListener(String id) {
                Intent intent = new Intent(ServiceMenuActivity.this, AptitudeDetailActivity.class);
                intent.putExtra("Flag", 1);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        showProgressDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", String.valueOf(pageNumber));
        map.put("pageSize", "10");
        map.put("type", type);
        presenter = new ServiceMenuPresenter(this);
        presenter.getMenuList(map);
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    /**
     * 获取数据
     *
     * @param entity
     */
    @Override
    public void initMenuList(ServiceMenuEntity entity) {
        if (entity != null) {
            List<ServiceMenuEntity.DataBean> beanList = entity.getData();
            if (!Utils.isStringEmpty(beanList)) {
                dismisProgressDialog();
                if (pageNumber == 1) {
                    recyclerView.refreshComplete();
                } else {
                    recyclerView.loadMoreComplete();
                }
                dataList.clear();
                dataList.addAll(beanList);
                adapter.notifyDataSetChanged();
                if (dataList.size() < 10) {
                    recyclerView.loadMoreComplete();
                }
            } else {
                dismisProgressDialog();
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        } else {
            dismisProgressDialog();
        }
    }

    @Override
    public void setPresenter(ServiceMenuContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
