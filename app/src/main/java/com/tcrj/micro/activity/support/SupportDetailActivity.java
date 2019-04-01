package com.tcrj.micro.activity.support;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.SupportEntity;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;

public class SupportDetailActivity extends BaseActivity {

    private String id;
    private TextView tvtitle;
    private ImageView backBtn;

    private TextView title;
    private TextView matterGist;
    private TextView gkCondition;
    private TextView gkProcedure;
    private TextView flow;
    private TextView timeLimit;
    private TextView chargeStandard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_detail);
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
        tvtitle.setText("内容");
        backBtn.setOnClickListener(new OnClick());

        title = (TextView) findViewById(R.id.title);
        matterGist = (TextView) findViewById(R.id.mattergist);
        gkCondition = (TextView) findViewById(R.id.gkcondition);
        gkProcedure = (TextView) findViewById(R.id.gkprocedure);
        flow = (TextView) findViewById(R.id.flow);
        timeLimit = (TextView) findViewById(R.id.timelimit);
        chargeStandard = (TextView) findViewById(R.id.chargestandard);
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
                    SupportEntity entity = JsonParse.getSupportDetail(jsonObject);
                    title.setText(entity.getTitle());
                    matterGist.setText(Html.fromHtml(entity.getMatterGist()));
                    gkCondition.setText(Html.fromHtml(entity.getGkCondition()));
                    gkProcedure.setText(Html.fromHtml(entity.getGkProcedure()));
                    flow.setText(Html.fromHtml(entity.getFlow()));
                    timeLimit.setText(Html.fromHtml(entity.getTimeLimit()));
                    chargeStandard.setText(Html.fromHtml(entity.getChargeStandard()));
                }

            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findGkInfoDetails, params, callback2);
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
