package com.tcrj.micro.activity.jzfp;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.base.BaseFragment;
import com.tcrj.micro.activity.left.LeftDetailActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.backqyinfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.until.ACache;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leict on 2018/6/28.
 */

public class jdglFragment extends BaseFragment implements View.OnClickListener {

    private MyOkHttp mMyOkhttp;
    private String bankid;
    private WebView mWebView = null;
    private TextView qyname;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;

    @Override
    protected int setLayout() {
        return R.layout.jdgl_fregment;
    }

    @Override
    protected void setView() {
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        bankid = ACache.get(getContext()).getAsString("bankid");


        textView1 = mRootView.findViewById(R.id.textView1);
        textView2 = mRootView.findViewById(R.id.textView2);
        textView3 = mRootView.findViewById(R.id.textView3);
        textView4 = mRootView.findViewById(R.id.textView4);
        textView5 = mRootView.findViewById(R.id.textView5);
        textView6 = mRootView.findViewById(R.id.textView6);

        qyname = mRootView.findViewById(R.id.qyname);
        mWebView = (WebView) mRootView.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);

        // 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);

    }

    private void setData(backqyinfo response) {
        qyname.setText(response.getName());

        String newContent = getNewContent(response.getQyjj());
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
    protected void setData() {
//        getBankinfo();
    }

    //获取产品所属金融企业信息
    private void getBankinfo() {

        showProgressDialog("正在加载...");
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("id", bankid);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.jrztbankqyinfo_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode,String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
                        dismisProgressDialog();
                        String errorCode = dataBean.getErrorcode();
                        if("9999".equals(errorCode)){

                            backqyinfo response = new Gson().fromJson(dataBean.getData(), backqyinfo.class);

                            setData(response);
                        }
                    }
                });

    }

    private List<backqyinfo> parseNoHeaderJArray(String strByJson) {

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();

        Gson gson = new Gson();
        List<backqyinfo> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            backqyinfo userBean = gson.fromJson(user, backqyinfo.class);
            userBeanList.add(userBean);
        }
        return userBeanList;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){

            case R.id.textView1:

                intent.setClass(mContext,ldjgActivity.class);
                break;

            case R.id.textView2:

                intent.setClass(mContext,gzzdActivity.class);
                break;
            case R.id.textView3:

                ACache.get(mContext).put("jzfptype","2");
                intent.setClass(mContext,jzfpActivity.class);
                break;
            case R.id.textView4:
                ACache.get(mContext).put("jzfptype","3");
                intent.setClass(mContext,jzfpActivity.class);
                break;
            case R.id.textView5:
                ACache.get(mContext).put("jzfptype","1");
                intent.setClass(mContext,jzfpActivity.class);
                break;
            case R.id.textView6:

                intent.setClass(mContext,fpgzActivity.class);
                break;


            default:
                break;
        }

        startActivity(intent);
    }
}
