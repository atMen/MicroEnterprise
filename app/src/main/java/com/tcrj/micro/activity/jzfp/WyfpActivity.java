package com.tcrj.micro.activity.jzfp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;

import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.entity.yqfpInfo;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public class WyfpActivity extends BaseActivity implements View.OnClickListener {

    private static final int EVENTYPE = 002;
    TextView txtTitle;
    ImageView btnback;
    private MyOkHttp mMyOkhttp;
    private Button btn_true;
    private EditText edt_jz;
    private EditText edt_zl;
    private EditText edt_jy;
    private EditText edt_cy;
    private EditText edt_sm;
    private EditText edt_qt;

    private String token;
    private String id;

    private fpjlListInfo.DataBean item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyfp);

         id = getIntent().getStringExtra("id");
         token = getIntent().getStringExtra("token");
         item = (fpjlListInfo.DataBean) getIntent().getExtras().getSerializable("fpxxKey");

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        initView();
    }

    @Override
    public void initView() {
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        btn_true = findViewById(R.id.btn_true);

        edt_jz = findViewById(R.id.edt_jz);
        edt_zl = findViewById(R.id.edt_zl);
        edt_jy = findViewById(R.id.edt_jy);
        edt_cy = findViewById(R.id.edt_cy);
        edt_sm = findViewById(R.id.edt_sm);
        edt_qt = findViewById(R.id.edt_qt);

        if(item != null){
            edt_jz.setText(item.getAidPoorMoney()+"");
            edt_zl.setText(item.getAidPoorIntelligence()+"");
            edt_jy.setText(item.getAidPoorJob()+"");
            edt_cy.setText(item.getAidPoorIndustry()+"");
            edt_sm.setText(item.getAidPoorBussiness()+"");
            edt_qt.setText(item.getAidPoorOtherType()+"");
            txtTitle.setText("信息修改");
        }else {
            txtTitle.setText("我要扶贫");

        }

        btn_true.setOnClickListener(this);


    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_true:
                String jz = edt_jz.getText().toString().trim();
                String zl = edt_zl.getText().toString().trim();
                String jy = edt_jy.getText().toString().trim();
                String cy = edt_cy.getText().toString().trim();
                String sm = edt_sm.getText().toString().trim();
                String qt = edt_qt.getText().toString().trim();

                getDataFromNet(jz,zl,jy,cy,sm,qt);

                break;

            default:
                break;
        }
    }


    //获取网络数据
    public void getDataFromNet(String jz, String zl, String jy, String cy, String sm, String qt) {

//      showProgressDialog();
        JSONObject jsonObject = new JSONObject();
        try {
            //TODO:修改token
            jsonObject.put("aidPoorObjectId", id);
            jsonObject.put("token", token);
            jsonObject.put("aidPoorMoney", jz);
            jsonObject.put("aidPoorIntelligence", zl);
            jsonObject.put("aidPoorJob", jy);
            jsonObject.put("aidPoorIndustry", cy);
            jsonObject.put("aidPoorBussiness", sm);
            jsonObject.put("aidPoorOtherType", qt);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.wyfp_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        dismisProgressDialog();

                        String errorcode = response.getErrorcode();
                        if("9999".equals(errorcode)){
                            Toast.makeText(WyfpActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                            sendMsg();
                            finish();
                        }


                    }
                });

    }

    private void sendMsg() {
        EventBus.getDefault().post(new MessageEvent("FP",EVENTYPE));
    }

}
