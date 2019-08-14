package com.tcrj.micro.activity.enterprise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.EnterpriseEntity;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;
import com.tcrj.micro.until.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.OnClickListener;

public class EnterpriseDetailActivity extends BaseActivity {

    private String id;
    private String title;
    private TextView tvtitle;
    private ImageView backBtn;
    private TextView entName;
    private TextView regNo;
    private TextView typeName;
    private TextView estDate;
    private TextView regCap;
    private TextView sfxwqy;
    private TextView tcyy;
    private TextView regOrgName;
    private TextView menleiName;
    private TextView industryName;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_detail);
        initView();
        getData();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("小微企业认定");

        entName = (TextView)findViewById(R.id.entname);
        regNo = (TextView)findViewById(R.id.regno);
        typeName = (TextView)findViewById(R.id.typename);
        estDate = (TextView)findViewById(R.id.estdate);
        regCap  = (TextView)findViewById(R.id.regcap);
        sfxwqy = (TextView)findViewById(R.id.sfxwqy);
        tcyy = (TextView)findViewById(R.id.tcyy);
        regOrgName = (TextView)findViewById(R.id.regorgname);
        menleiName = (TextView)findViewById(R.id.menleiname);
        industryName = (TextView)findViewById(R.id.industryname);
        time = findViewById(R.id.time);

        time.setText(DateUtil.getCurrentDate());
        backBtn.setOnClickListener(new OnClick());


    }

    @Override
    public void getData() {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                    EnterpriseEntity entity = JsonParse.getEnterpriseDetail(jsonObject);


                    entName.setText(entity.getEntName());
                    regNo.setText(entity.getRegNo());
                    typeName.setText(entity.getTypeName());
                    estDate.setText(entity.getEstDate());
                    regCap.setText(entity.getRegCap()+"万元");

                    regOrgName.setText(entity.getRegOrgName());
                    menleiName.setText(entity.getMenleiName());
                    industryName.setText(entity.getIndustryName());

                    sfxwqy.setText(entity.getXwstate_cn());
                    tcyy.setText(entity.getEmreason_cn());
//                    time.setText(entity.getQuerytime().substring(0,10));

                }

            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findXwqyInfoDetails, params, callback2);
    }

    class OnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnback:
                    finish();
                    break;

            }
        }
    }




}
