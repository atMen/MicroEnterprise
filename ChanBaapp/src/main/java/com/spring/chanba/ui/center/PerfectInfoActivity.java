package com.spring.chanba.ui.center;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.spring.chanba.R;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.CommunalEntity;
import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.PersonInfoEntity;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.contract.UserInfoContract;
import com.spring.chanba.presenter.UserInfoPresenter;

import java.util.HashMap;

/**
 * 完善个人信息
 */
@Route(path = "/test/PerfectInfoActivity")
public class PerfectInfoActivity extends BaseActivity implements View.OnClickListener, UserInfoContract.View {
    private ImageButton imgTitleBack;
    private TextView tvTitleHead;
    private EditText edtPerfectName;
    private EditText edtPerfectIdnumber;
    private EditText edtPerfectPhone;
    private EditText edtPerfectAddress;
    private EditText edtPerfectSchool;
    private EditText edtPerfectEducation;
    private EditText edtPerfectJob;
    private EditText edtPerfectWorkaddr;
    private EditText edtPerfectYear;
    private Button btnPerfectSubmit;
    private UserInfoContract.Presenter presenter;
    private RadioGroup rgpPerfectPerson;
    private RadioButton rbtPerfectMan;
    private RadioButton rbtPerfectWoman;
    private RadioGroup rgpPerfectState;
    private RadioButton rbtPerfectYes;
    private RadioButton rbtPerfectNo;
    private CommunalEntity communal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfectinfo);
        setCommunal();
        initView();
//        loadData();
    }

    private void setCommunal() {
        communal = new CommunalEntity();
        communal.setSex("0");
        communal.setFlag("1");
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgTitleBack = (ImageButton) findViewById(R.id.img_title_back);
        tvTitleHead = (TextView) findViewById(R.id.tv_title_head);
        edtPerfectName = (EditText) findViewById(R.id.edt_perfect_name);
        edtPerfectIdnumber = (EditText) findViewById(R.id.edt_perfect_idnumber);
        edtPerfectPhone = (EditText) findViewById(R.id.edt_perfect_phone);
        edtPerfectAddress = (EditText) findViewById(R.id.edt_perfect_address);
        edtPerfectSchool = (EditText) findViewById(R.id.edt_perfect_school);
        edtPerfectEducation = (EditText) findViewById(R.id.edt_perfect_education);
        edtPerfectJob = (EditText) findViewById(R.id.edt_perfect_job);
        edtPerfectWorkaddr = (EditText) findViewById(R.id.edt_perfect_workaddr);
        edtPerfectYear = (EditText) findViewById(R.id.edt_perfect_year);
        rgpPerfectPerson = (RadioGroup) findViewById(R.id.rgp_perfect_person);
        rbtPerfectMan = (RadioButton) findViewById(R.id.rbt_perfect_man);
        rbtPerfectWoman = (RadioButton) findViewById(R.id.rbt_perfect_woman);
        rgpPerfectState = (RadioGroup) findViewById(R.id.rgp_perfect_state);
        rbtPerfectYes = (RadioButton) findViewById(R.id.rbt_perfect_yes);
        rbtPerfectNo = (RadioButton) findViewById(R.id.rbt_perfect_no);
        btnPerfectSubmit = (Button) findViewById(R.id.btn_perfect_submit);
        imgTitleBack.setVisibility(View.VISIBLE);
        imgTitleBack.setOnClickListener(this);
        btnPerfectSubmit.setOnClickListener(this);
        tvTitleHead.setText("完善信息");
        rgpPerfectPerson.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbt_perfect_man) {
                    communal.setSex("0");

                } else if (checkedId == R.id.rbt_perfect_woman) {
                    communal.setSex("1");

                }
            }
        });
        rgpPerfectState.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbt_perfect_no) {
                    communal.setFlag("0");

                } else if (checkedId == R.id.rbt_perfect_yes) {
                    communal.setFlag("1");

                }
            }
        });
    }

    private void loadData() {
        UserInfoEntity.DataBean bean = BaseApplication.getUserInfo();
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", bean.getId());
        presenter = new UserInfoPresenter(this);
        presenter.getMember(map);
    }

    /**
     * 确认提交
     */
    private void submit() {
        String name = edtPerfectName.getText().toString().trim();
        String number = edtPerfectIdnumber.getText().toString().trim();
        String phone = edtPerfectPhone.getText().toString().trim();
        String address = edtPerfectAddress.getText().toString().trim();
        String school = edtPerfectSchool.getText().toString().trim();
        String education = edtPerfectEducation.getText().toString().trim();
        String job = edtPerfectJob.getText().toString().trim();
        String workaddr = edtPerfectWorkaddr.getText().toString().trim();
        String fectyear = edtPerfectYear.getText().toString().trim();
        UserInfoEntity.DataBean entity = BaseApplication.getUserInfo();
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", entity.getId());
        map.put("name", name);
        map.put("IDNumber", number);
        map.put("sex", communal.getSex());
        map.put("phone", phone);
        map.put("address", address);
        map.put("university", school);
        map.put("education", education);
        map.put("profession", job);
        map.put("workplace", workaddr);
        map.put("workingLife", fectyear);
        map.put("isFund", communal.getFlag());
        presenter = new UserInfoPresenter(this);
        presenter.getData(map);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_title_back) {
            finish();

        } else if (i == R.id.btn_perfect_submit) {
            submit();

        }
    }

    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    /**
     * 请求数据
     *
     * @param entity
     */
    @Override
    public void initData(HandleEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                displayToast(entity.getMessage());
                finish();
            } else {
                displayToast(entity.getMessage());
            }
        }
    }

    @Override
    public void initMember(PersonInfoEntity entity) {
        if (entity != null) {
            edtPerfectName.setText(entity.getData().getName());
            edtPerfectIdnumber.setText(entity.getData().getIDNumber());
            edtPerfectPhone.setText(entity.getData().getPhone());
            edtPerfectAddress.setText(entity.getData().getAddress());
            edtPerfectSchool.setText(entity.getData().getUniversity());
            edtPerfectEducation.setText(entity.getData().getEducation());
            edtPerfectJob.setText(entity.getData().getProfession());
            edtPerfectWorkaddr.setText(entity.getData().getWorkplace());
            edtPerfectYear.setText(entity.getData().getWorkingLife());
            if (entity.getData().getSex().equals("0")) {
                rbtPerfectMan.setChecked(true);
            } else {
                rbtPerfectWoman.setChecked(true);
            }
            if (entity.getData().getIsFund().equals("1")) {
                rbtPerfectYes.setChecked(true);
            } else {
                rbtPerfectNo.setChecked(true);
            }
        } else {
            displayToast("请完善个人信息");
        }
    }

    @Override
    public void setPresenter(UserInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
