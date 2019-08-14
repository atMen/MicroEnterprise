package com.spring.chanba.ui.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.adapter.TalentLoanAdapter;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.TalentLoanEntity;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.contract.TalentLoanContract;
import com.spring.chanba.dialog.MessageDialogBuilder;
import com.spring.chanba.presenter.TalentLoanPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 人才贷款
 */
public class TalentLoanActivity extends BaseActivity implements View.OnClickListener, TalentLoanContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private ViewPager viewPager;
    private TalentLoanAdapter adapter;
    private Button btnTalentSubmit;
    private String ids;
    private TalentLoanContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talentloan);
        initView();
        loadData();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        ids = getIntent().getStringExtra("id");
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        viewPager = (ViewPager) findViewById(R.id.talent_viewpager);
        btnTalentSubmit = (Button) findViewById(R.id.btn_talent_submit);
        imgTitleBack.setOnClickListener(this);
        btnTalentSubmit.setOnClickListener(this);
        imgTitleBack.setVisibility(View.VISIBLE);
        tvTitleHead.setText("贷款推荐");
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        } else if (i == R.id.btn_talent_submit) {
            showProgressDialog();
            UserInfoEntity.DataBean bean = BaseApplication.getUserInfo();
            HashMap<String, String> map = new HashMap<>();
            map.put("productId", ids);
            map.put("memberId", bean.getId());
            presenter = new TalentLoanPresenter(this);
            presenter.getData(map);
        }
    }

    /**
     * 请求数据
     */
    private void loadData() {
        String[] strTitle = {"好工作", "好学历", "好学历"};
        String[] strValue1 = {"1.大专(含)以上学历", "1.\"985\"或者\"211\"或者统招本科以上", "1.全日制统招硕士及以上学历"};
        String[] strValue2 = {"2.优质行职业", "2.工作地点在我行网点城市", "2.工作地点在我行网点城市"};
        String[] strValue3 = {"3.缴纳住房公积金月3500以上", "3.工作稳定且不少于两年", "3.工作稳定且不少于两年"};
        List<TalentLoanEntity> dataList = new ArrayList<>();
        for (int i = 0; i < strTitle.length; i++) {
            TalentLoanEntity entity = new TalentLoanEntity();
            entity.setTitle(strTitle[i].toString());
            entity.setContent1(strValue1[i].toString());
            entity.setContent2(strValue2[i].toString());
            entity.setContent3(strValue3[i].toString());
            dataList.add(entity);
        }
        adapter = new TalentLoanAdapter(this);
        adapter.setData(dataList);
        viewPager.setOffscreenPageLimit(dataList.size());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initData(HandleEntity entity) {
        dismisProgressDialog();
        if (entity != null) {
            if (entity.getState() == 1) {
                final MessageDialogBuilder builder = MessageDialogBuilder.getInstance(this);
                builder.setTitles("您已申请过此产品")
                        .setContents("查看申请记录")
                        .setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                            @Override
                            public void setSubmitListener() {
                                openNewActivity(TalentLoanListActivity.class);
                            }

                            @Override
                            public void setCancelListener() {
                                builder.dismiss();
                            }
                        });
                builder.show();
            } else {
                displayToast(entity.getMessage());
            }
        }
    }

    @Override
    public void setPresenter(TalentLoanContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
