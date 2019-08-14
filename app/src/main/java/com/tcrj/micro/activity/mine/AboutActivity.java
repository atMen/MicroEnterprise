package com.tcrj.micro.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.VolleyUtil;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.WxShareUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;

public class AboutActivity extends BaseActivity {

    private TextView tvtitle;
    private ImageView backBtn;
    private WebView mWebView = null;
    private TextView tv_more;
    private String pgyUrl = "https://www.pgyer.com/iY1X";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        getData();
    }

    @Override
    public void initView() {
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        tv_more = (TextView) findViewById(R.id.tv_more);
        tv_more.setText("分享");
        tv_more.setVisibility(View.VISIBLE);

        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("关于我们");
        mWebView = (WebView) findViewById(R.id.webView);
        backBtn.setOnClickListener(new OnClick());
        tv_more.setOnClickListener(new OnClick());



        mWebView.loadUrl(pgyUrl);

//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public void getData() {
//        showProgressDialog();
//        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id", "vEbUnq");
//
//        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {
//
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                dismisProgressDialog();
//                Log.d("aa", jsonObject.toString());
//                if (JsonParse.getMsgByKey(jsonObject, "state").equals("1")) {
//                    InfoEntity entity = JsonParse.getInfoDetail(jsonObject);
//                    mWebView.loadDataWithBaseURL(null, entity.getInfoContent(), "text/html", "UTF-8", null);
//                }
//
//            }
//
//            @Override
//            public void onFailed(String result) {
//                dismisProgressDialog();
//                handler.sendEmptyMessage(11);
//            }
//        };
//        volleyUtil.getJsonDataFromServer(Constant.findInfoDetails, params, callback2);

    }


    class OnClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {

                case R.id.btnback:
                    finish();
//                    WxShareUtils.share2Wx(AboutActivity.this,true,Constant.appID);
                    break;

                case R.id.tv_more:
                    WxShareUtils.shareWeb(AboutActivity.this,Constant.appID,pgyUrl,"小微库","点击进入小微库app下载页面",R.mipmap.lunch_icon);
//                  WxShareUtils.share2Wx(AboutActivity.this,true,Constant.appID);
                    break;
            }
        }
    }



}
