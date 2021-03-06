package com.spring.chanba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.spring.chanba.R;
import com.spring.chanba.adapter.LawCaseListAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.contract.LawCaseContract;
import com.spring.chanba.presenter.LawCasePresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.xrecycler.ProgressStyle;
import com.spring.chanba.xrecycler.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 法律案例
 */
public class LawCaseListActivity extends BaseActivity implements LawCaseContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private XRecyclerView recyclerView;
    private int pageNumber = 1;
    private LawCaseListAdapter adapter;
    private LawCaseContract.Presenter presenter;
    private List<LawServiceGridEntity.DataBean> dataList;

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
        tvTitleHead.setText("经典案例");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        //recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        recyclerView.getDefaultFootView().setLoadingHint("努力加载中...");
        recyclerView.getDefaultFootView().setNoMoreHint("加载完成");
        recyclerView.setLimitNumberToCallLoadMore(2);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNumber = 1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                pageNumber++;
                loadData();
            }
        });
        dataList = new ArrayList<>();
        adapter = new LawCaseListAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.refresh();
        adapter.setOnItemClickListener(new LawCaseListAdapter.OnItemCallBack() {
            @Override
            public void setOnClickListener(String id) {
                Intent intent = new Intent(LawCaseListActivity.this, WebViewCaseActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    /**
     * 请求数据
     */
    private void loadData() {
        showProgressDialog();
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", String.valueOf(pageNumber));
        map.put("pageSize", "10");
        presenter = new LawCasePresenter(this);
        presenter.getLawCase(map);
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initLawCase(LawServiceGridEntity entity) {
        if (entity != null) {
            List<LawServiceGridEntity.DataBean> beanList = entity.getData();
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
    public void setPresenter(LawCaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
