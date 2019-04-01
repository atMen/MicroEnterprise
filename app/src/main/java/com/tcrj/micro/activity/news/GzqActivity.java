package com.tcrj.micro.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.newui.hzwlistview.xlist.XListView;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;

public class GzqActivity extends BaseActivity {

    private TextView tvtitle;
    private ImageView backBtn;
    private WebView mWebView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzq);
        initView();
        getData();
    }

    @Override
    public void initView() {
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("个转企");
        mWebView = (WebView) findViewById(R.id.webView);
        backBtn.setOnClickListener(new OnClick());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public void getData() {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "6bArua");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
                    InfoEntity entity = JsonParse.getInfoDetail(jsonObject);
                    mWebView.loadDataWithBaseURL(null, entity.getInfoContent(), "text/html", "UTF-8", null);
                }

            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findInfoDetails, params, callback2);

    }


    class OnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.btnback:
                    finish();
                    break;
            }
        }
    }



}
