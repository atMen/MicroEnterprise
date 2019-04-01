package com.spring.chanba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spring.chanba.R;
import com.spring.chanba.adapter.BusinessHatchAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.ProjectDisplayContract;
import com.spring.chanba.presenter.ProjectDisplayPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.xrecycler.ProgressStyle;
import com.spring.chanba.xrecycler.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 创业项目
 */
public class ProjectDisplayFragment extends Fragment implements ProjectDisplayContract.View {
    private XRecyclerView recyclerView;
    private BusinessHatchAdapter adapter;
    private List<LawServiceListEntity.DataBean> dataList;
    private int pageNumber = 1;
    private ProjectDisplayContract.Presenter presenter;

    public static ProjectDisplayFragment newInstance(String flag) {
        ProjectDisplayFragment fragment = new ProjectDisplayFragment();
        Bundle b = new Bundle();
        b.putString("categoryId", flag);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        initView(view);
        loadData();
        return view;
    }

    /**
     * 获取页面控件
     *
     * @param view
     */
    private void initView(View view) {
        recyclerView = (XRecyclerView) view.findViewById(R.id.recycler_infomation);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setPullRefreshEnabled(false);
        //recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
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
        adapter = new BusinessHatchAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BusinessHatchAdapter.OnItemCallBack() {
            @Override
            public void setOnClickListener(String hatchId) {
                Log.i("ProjectDisplayFragment", hatchId);
                Intent intent = new Intent(getContext(), ProjectDisplayDetailActivity.class);
                intent.putExtra("id", hatchId);
                intent.putExtra("Flag", 1);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        Bundle b = getArguments();
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", String.valueOf(pageNumber));
        map.put("pageSize", "10");
        map.put("proKinds", b.getString("categoryId"));
        presenter = new ProjectDisplayPresenter(this);
        presenter.getData(map);
    }

    @Override
    public void failedMessage(String message) {
        ((BaseActivity) getContext()).displayToast(message);
    }

    @Override
    public void initData(LawServiceListEntity entity) {
        if (entity != null) {
            List<LawServiceListEntity.DataBean> beanList = entity.getData();
            if (!Utils.isStringEmpty(beanList)) {
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
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        }
    }

    @Override
    public void setPresenter(ProjectDisplayContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
