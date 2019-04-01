package com.spring.chanba.ui.center;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.TrainEnrolmentAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.TrainEnrolmentEntity;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.contract.TrainEnrolmentContract;
import com.spring.chanba.presenter.TrainEnrolmentPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.xrecycler.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 培训报名
 */
public class TrainEnrolmentActivity extends BaseActivity implements TrainEnrolmentContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private XRecyclerView recyclerView;
    private TrainEnrolmentAdapter adapter;
    private List<TrainEnrolmentEntity.DataBean> dataList;
    private TrainEnrolmentContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initView();
        loadData();
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
        tvTitleHead.setText("培训报名");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);

        dataList = new ArrayList<>();
        adapter = new TrainEnrolmentAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 请求数据
     */
    private void loadData() {
        UserInfoEntity.DataBean bean = BaseApplication.getUserInfo();
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", bean.getId());
        presenter = new TrainEnrolmentPresenter(this);
        presenter.getData(map);
    }

    /**
     * 返回服务器失败消息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initData(TrainEnrolmentEntity entity) {
        if (entity != null) {
            List<TrainEnrolmentEntity.DataBean> beanList = entity.getData();
            if (!Utils.isStringEmpty(beanList)) {
                dataList.addAll(beanList);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setPresenter(TrainEnrolmentContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
