package com.tcrj.micro.activity.jrtz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.CheckInfo;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.bankExample;
import com.tcrj.micro.entity.bankalInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class jralInfoActivity extends BaseActivity implements View.OnClickListener {


    private MyOkHttp mMyOkhttp;
    private String id;
    private String type;
    private WebView mWebView = null;
    private TextView content_title;
    private TextView content_time;
    private MyTextViewZH txtTitle;
    private ImageView btnback;
    private Button btn_sq;

    private bankExample bankInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jral_info);

        EventBus.getDefault().register(this);
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");

        btn_sq = findViewById(R.id.btn_sq);
        btnback = findViewById(R.id.btnback);
        content_title = findViewById(R.id.content_title);
        content_time = findViewById(R.id.content_time);
        txtTitle = findViewById(R.id.txtTitle);

        if("0".equals(type)){
            btn_sq.setVisibility(View.GONE);
        }

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);

        // 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        btnback.setOnClickListener(this);
        btn_sq.setOnClickListener(this);
        txtTitle.setText("详情");
        getData();

    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {
        showProgressDialog();
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("id", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url("0".equals(type)? ApiConstants.jrztbankalinfo_Api : ApiConstants.jrztbankcpinfo_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(jralInfoActivity.this, error_msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        dismisProgressDialog();
                        String errorCode = dataBean.getErrorcode();
                        if("9999".equals(errorCode)){
                            bankExample response = new Gson().fromJson(dataBean.getData(), bankExample.class);
                            bankInfo = response;

                            setData(response);
                        }
                    }
                });
    }

    private void setData(bankExample response) {
        content_title.setText("0".equals(type)? response.getTitle() : response.getName());
        String createDate = response.getCreateDate();
        content_time.setText(createDate.substring(0,10));
        String newContent = getNewContent(response.getContent());

        mWebView.loadDataWithBaseURL(null, newContent, "text/html", "UTF-8", null);


    }
    public static String getNewContent(String htmltext){

        try {
            Document doc= Jsoup.parse(htmltext);
            Elements element =doc.getElementsByTag("img");
            for (Element elementimg : element) {
                elementimg.attr("style","border: 0px; display: block; margin: auto; max-width:100%;");
            }

            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_sq:
                checkToken();

                break;

            case R.id.btnback:
                finish();
                break;

            default:
                break;
        }

    }

    private void checkToken() {
        String token = ACache.get(this).getAsString("token");
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.checktoken_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<CheckInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Toast.makeText(jralInfoActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, CheckInfo response) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();

                        String errorCode = response.getErrorcode();
                        if("9999".equals(errorCode)){

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("backinfo",bankInfo);
                            toClass(jralInfoActivity.this,JrcpSqActivity.class,bundle);

                        }else{
                            ACache.get(jralInfoActivity.this).clear();
                            Intent intent = new Intent();
//                          intent.putExtra("openid",-2);
                            intent.setClass(jralInfoActivity.this, LoginActivity.class);
                            jralInfoActivity.this.startActivity(intent);
                        }

                    }
                });
    }
    /**
     * 拦截返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
            // 如果不是back键正常响应
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void back() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Log.e("TAG","Event ");
        switch (messageEvent.getType()){

            case 007:

                btn_sq.setText("已申请该产品");
                btn_sq.setEnabled(false);
                btn_sq.setBackgroundColor(getResources().getColor(R.color.gray));
                break;

            default:
                break;
        }

    }


}
