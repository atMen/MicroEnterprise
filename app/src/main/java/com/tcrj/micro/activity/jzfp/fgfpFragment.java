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

public class fgfpFragment extends BaseFragment  {

    private MyOkHttp mMyOkhttp;


    @Override
    protected int setLayout() {
        return R.layout.fgfp_fragment;
    }

    @Override
    protected void setView() {
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();


    }

    private void setData(backqyinfo response) {

    }


    @Override
    protected void setData() {

    }






}
