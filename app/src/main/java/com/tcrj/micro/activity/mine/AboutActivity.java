package com.tcrj.micro.activity.mine;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

                    showDialog();
//                    WxShareUtils.
//                            shareWeb(AboutActivity.this,Constant.appID,pgyUrl,"小微库"
//                                    ,"点击进入小微库app下载页面",R.drawable.logo,true);
//                  WxShareUtils.share2Wx(AboutActivity.this,true,Constant.appID);
                    break;

                default:
                    break;
            }
        }

        Dialog dialog;
        private void showDialog() {
            dialog = new Dialog(AboutActivity.this,R.style.dialog);
            View view_dialog = LayoutInflater.from(AboutActivity.this).inflate(
                    R.layout.dialog_layout, null);
            dialog.setContentView(view_dialog);

            LinearLayout hy = (LinearLayout) view_dialog.findViewById(R.id.ll_hy);
            LinearLayout pyq = (LinearLayout) view_dialog.findViewById(R.id.ll_pyq);
            TextView tv_cancle = (TextView) view_dialog.findViewById(R.id.tv_cancle);
            tv_cancle.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dialog.dismiss();
                }
            });

            //分享到微信好友
            hy.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    WxShareUtils.
                            shareWeb(AboutActivity.this,Constant.appID,pgyUrl,"小微库"
                                    ,"点击进入小微库app下载页面",R.drawable.logo,true);
                    dialog.dismiss();
                }
            });

            //分享到微信朋友圈
            pyq.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    WxShareUtils.
                            shareWeb(AboutActivity.this,Constant.appID,pgyUrl,"小微库"
                                    ,"点击进入小微库app下载页面",R.drawable.logo,false);
                    dialog.dismiss();
                }
            });
        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
            Window dialogWindow = dialog.getWindow();
            //设置位置
            dialogWindow.setGravity(Gravity.BOTTOM);
            //设置dialog的宽高属性
            dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
        }
    }



}
