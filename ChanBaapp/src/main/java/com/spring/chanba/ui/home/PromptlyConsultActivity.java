package com.spring.chanba.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.spring.chanba.R;
import com.spring.chanba.adapter.PromptlyConsultAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.PromptlyConsultEntity;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.contract.PromptlyConsultContract;
import com.spring.chanba.presenter.PromptlyConsultPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.xrecycler.ProgressStyle;
import com.spring.chanba.xrecycler.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 立即咨询
 */
public class PromptlyConsultActivity extends BaseActivity implements View.OnClickListener, PromptlyConsultContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private XRecyclerView recyclerView;
    private PromptlyConsultAdapter adapter;
    private PromptlyConsultContract.Presenter presenter;
    private int pageNumber = 1;
    private List<PromptlyConsultEntity.DataBean> dataList;
    private EditText edtPromptlyContent;
    private Button btnPromptlySubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promptlyconsult);
        initView();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        recyclerView = (XRecyclerView) findViewById(R.id.p_recyclerview);
        edtPromptlyContent = (EditText) findViewById(R.id.edt_promptly_content);
        btnPromptlySubmit = (Button) findViewById(R.id.btn_promptly_submit);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(this);
        btnPromptlySubmit.setOnClickListener(this);
        tvTitleHead.setText("立即咨询");
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
        adapter = new PromptlyConsultAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    /**
     * 请求数据
     */
    private void loadData() {
        UserInfoEntity.DataBean bean = BaseApplication.getUserInfo();
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", bean.getId());
        map.put("pageIndex", String.valueOf(pageNumber));
        map.put("pageSize", "10");
        presenter = new PromptlyConsultPresenter(this);
        presenter.getData(map);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        } else if (i == R.id.btn_promptly_submit) {
            String content = edtPromptlyContent.getText().toString().trim();
            if (Utils.isStringEmpty(content)) {
                displayToast("请输入您要咨询的内容");
                return;
            }
            submit();

        }
    }

    /**
     * 提交咨询
     */
    private void submit() {
        UserInfoEntity.DataBean bean = BaseApplication.getUserInfo();
        String content = edtPromptlyContent.getText().toString().trim();
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", bean.getId());
        map.put("content", content);
        presenter = new PromptlyConsultPresenter(this);
        presenter.submit(map);
    }

    /**
     * 返回服务器消息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initData(PromptlyConsultEntity entity) {
        if (entity != null) {
            List<PromptlyConsultEntity.DataBean> beanList = entity.getData();
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

    /**
     * 提交咨询
     *
     * @param entity
     */
    @Override
    public void initSend(HandleEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                displayToast(entity.getMessage());
                edtPromptlyContent.setText("");
                loadData();
            } else {
                displayToast(entity.getMessage());
            }
        }
    }

    @Override
    public void setPresenter(PromptlyConsultContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
