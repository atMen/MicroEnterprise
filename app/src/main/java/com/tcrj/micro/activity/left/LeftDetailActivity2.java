package com.tcrj.micro.activity.left;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.FuchiEntity;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;
import com.tcrj.micro.view.MyTextViewXH;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.OnClickListener;

public class LeftDetailActivity2 extends BaseActivity {

    private String id;
    private TextView tvtitle;
    private ImageView backBtn;

    private MyTextViewXH entName;
    private MyTextViewXH regNo;
    private MyTextViewXH yiju;
    private MyTextViewXH neirong;
    private MyTextViewXH shue;
    private MyTextViewXH bumenName;
    private MyTextViewXH ssDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_detail2);
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
        tvtitle.setText("享受扶持信息");
        backBtn.setOnClickListener(new OnClick());

        entName = (MyTextViewXH) findViewById(R.id.entname);
        regNo = (MyTextViewXH) findViewById(R.id.regno);
        yiju = (MyTextViewXH) findViewById(R.id.yiju);
        neirong = (MyTextViewXH) findViewById(R.id.neirong);
        shue = (MyTextViewXH) findViewById(R.id.shue);
        bumenName = (MyTextViewXH) findViewById(R.id.bumenname);
        ssDate = (MyTextViewXH) findViewById(R.id.ssdate);
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
                    FuchiEntity entity = JsonParse.getFuchiDetail(jsonObject);
                    entName.setText(entity.getEntName());
                    regNo.setText(entity.getRegNo());
                    yiju.setText(entity.getYiju());
                    neirong.setText(entity.getNeirong());
                    shue.setText(entity.getShue()+"万元");
                    bumenName.setText(entity.getBumenName());
                    ssDate.setText(entity.getSsDate());
                }
            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findXwqyXsfcInfoDetails, params, callback2);
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
