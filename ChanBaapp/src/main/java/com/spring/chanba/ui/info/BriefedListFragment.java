package com.spring.chanba.ui.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spring.chanba.R;
import com.spring.chanba.adapter.InformationListAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.bean.InformationEntity;
import com.spring.chanba.contract.InformationContract;
import com.spring.chanba.presenter.InformationPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.xrecycler.ProgressStyle;
import com.spring.chanba.xrecycler.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 资讯
 */
public class BriefedListFragment extends Fragment implements InformationContract.View {
    private InformationContract.Presenter presenter;
    private int pageNumber = 1;
    private XRecyclerView recyclerView;
    private InformationListAdapter adapter;
    private List<InformationEntity.DataBean> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        initView(view);
        return view;
    }

    public static BriefedListFragment newInstance(String flag) {
        BriefedListFragment fragment = new BriefedListFragment();
        Bundle b = new Bundle();
        b.putString("flag", flag);
        fragment.setArguments(b);
        return fragment;
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
        adapter = new InformationListAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.refresh();
    }

    /**
     * 请求数据
     */
    private void loadData() {
        Bundle b = getArguments();
        HashMap<String, String> map = new HashMap<>();
        map.put("pageIndex", String.valueOf(pageNumber));
        map.put("pageSize", "10");
        map.put("code", b.getString("flag"));
        presenter = new InformationPresenter(this);
        presenter.getData(map);
    }

    /**
     * 返回服务器失败信息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        ((BaseActivity) getContext()).displayToast(message);
    }

    /**
     * 请求服务器数据
     *
     * @param entity
     */
    @Override
    public void initData(InformationEntity entity) {
        if (entity != null) {
            List<InformationEntity.DataBean> beanList = entity.getData();
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
    public void setPresenter(InformationContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
