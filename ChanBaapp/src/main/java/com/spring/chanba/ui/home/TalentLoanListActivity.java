package com.spring.chanba.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.TalentLoanListAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.TalentLoanListEntity;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.contract.TalentLoanListContract;
import com.spring.chanba.presenter.TalentLoanListPresenter;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.NListView;

import java.util.HashMap;

/**
 * 服务申请
 */
public class TalentLoanListActivity extends BaseActivity implements TalentLoanListContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private NListView listView;
    private TalentLoanListAdapter adapter;
    private TalentLoanListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talentloanlist);
        initView();
        loadData();
    }

    /**
     * 请求数据
     */
    private void loadData() {
        showProgressDialog();
        UserInfoEntity.DataBean bean = BaseApplication.getUserInfo();
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", bean.getId());
        presenter = new TalentLoanListPresenter(this);
        presenter.getData(map);
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        listView = (NListView) findViewById(R.id.talent_listview);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleHead.setText("服务申请");
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initData(TalentLoanListEntity entity) {
        if (entity != null) {
            dismisProgressDialog();
            if (!Utils.isStringEmpty(entity.getData())) {
                adapter = new TalentLoanListAdapter(this);
                adapter.setData(entity.getData());
                listView.setAdapter(adapter);
            }
        } else {
            dismisProgressDialog();
        }
    }

    @Override
    public void setPresenter(TalentLoanListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
