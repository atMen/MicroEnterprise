package com.tcrj.micro.activity.jrtz;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.base.BaseFragment;
import com.tcrj.micro.adpater.jrcpAdapter;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.backqyinfo;
import com.tcrj.micro.entity.bankExample;
import com.tcrj.micro.entity.bankalInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CustomLoadMoreView;
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

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by leict on 2018/6/28.
 */

public class gsjjFragment extends BaseFragment  {

    private MyOkHttp mMyOkhttp;
    private String bankid;
    private WebView mWebView = null;
    private TextView qyname;

    @Override
    protected int setLayout() {
        return R.layout.gsjj_fregment;
    }

    @Override
    protected void setView() {
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        bankid = ACache.get(getContext()).getAsString("bankid");

        qyname = mRootView.findViewById(R.id.qyname);
        mWebView = (WebView) mRootView.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);

        // 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
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
        getBankinfo();
    }

    backqyinfo response;
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

                            List<backqyinfo> backqyinfos = parseNoHeaderJArray(dataBean.getData());
                            response = backqyinfos.get(0);
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

}
