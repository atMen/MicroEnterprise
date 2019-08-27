package com.tcrj.micro.activity;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.VolleyUtil;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.newui.hzwlistview.xlist.XListView;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.enterprise.EnterpriseDetailActivity;
import com.tcrj.micro.activity.enterprise.EnterpriseFindActivity;
import com.tcrj.micro.activity.news.NewsDetailActivity;
import com.tcrj.micro.activity.news.NewsFindActivity;
import com.tcrj.micro.adpater.EnterpriseListAdapter;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.EnterpriseEntity;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EnterpriseFragment extends Fragment {

    private PDFView pdfView;
    private View fragmetView;
    private TextView tvtitle;
    private ImageView btnsearch;
    private XListView listview;
    private ArrayList<EnterpriseEntity> list = new ArrayList<EnterpriseEntity>();
    public static EnterpriseListAdapter adapter;
    private int pageIndex = 1;
    private Dialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.enterprise_fragment, container, false);
        initView();
//        getData();
        return fragmetView;

    }

    public void initView() {

        pdfView = (PDFView) fragmetView.findViewById(R.id.pdfView);
        tvtitle = (TextView) fragmetView.findViewById(R.id.txtTitle);
        btnsearch = (ImageView) fragmetView.findViewById(R.id.btnsearch);

        btnsearch.setVisibility(View.VISIBLE);
        tvtitle.setText("小微企业认定");
        listview = (XListView) fragmetView.findViewById(R.id.listview);
        listview.setPullLoadEnable(true);
        listview.setXListViewListener(new XListView.IXListViewListener() {

            @Override
            public void onRefresh() {
                listview.setRefreshTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
                pageIndex = 1;
                getFreshVolley(pageIndex);
            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                getLoadVolley(pageIndex);
            }
        });
        adapter = new EnterpriseListAdapter(getActivity(),list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClick());
        btnsearch.setOnClickListener(new OnClick());

        showProgressDialog();
        pdfView.fromAsset("xwqyk.pdf")
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .spacing(10) // in dp
                .pageFitPolicy(FitPolicy.BOTH)
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        dismisProgressDialog();
                        Log.e("TAG","加载错误");
                    }
                })

                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages) {
                        dismisProgressDialog();
                    }
                })
                .load();

    }

    public void getData() {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(getActivity(), handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex);
        params.put("siteId", "JfAJJr");
        params.put("entparam", "");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aaa", jsonObject.toString());
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    list.clear();
                    ArrayList<EnterpriseEntity> arraylist = JsonParse.getEnterpriseList(jsonObject);
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        listview.setPullLoadEnable(false);
                    }else{
                        listview.setPullLoadEnable(true);
                    }
                }else{
                    list.clear();
                    listview.setPullLoadEnable(false);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findXwqyList, params, callback2);

    }

    // 刷新
    public void getFreshVolley(int pageNo) {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(getActivity(), handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex );
        params.put("siteId", "JfAJJr");
        params.put("entparam", "");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<EnterpriseEntity> arraylist = JsonParse.getEnterpriseList(jsonObject);
                    list.clear();
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        listview.setPullLoadEnable(false);
                    }else{
                        listview.setPullLoadEnable(true);
                    }
                }else{
                    listview.setPullLoadEnable(false);
                    list.clear();

                }
                listview.stopRefresh();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String result) {
                listview.stopRefresh();
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findXwqyList, params, callback2);

    }

    // 加载
    public void getLoadVolley(int pageNo) {
        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(getActivity(), handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pagesize", 10);
        params.put("pageindex", pageIndex );
        params.put("siteId", "JfAJJr");
        params.put("entparam", "");

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
                    ArrayList<EnterpriseEntity> arraylist = JsonParse.getEnterpriseList(jsonObject);
                    list.addAll(arraylist);
                    if(arraylist.size()<10){
                        listview.setPullLoadEnable(false);
                    }else{
                        listview.setPullLoadEnable(true);
                    }
                }else{
                    listview.setPullLoadEnable(false);
                }
                listview.setSelection(list.size() - 1);
                listview.stopLoadMore();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String result) {
                listview.stopLoadMore();
                dismisProgressDialog();
                handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.findXwqyList, params, callback2);

    }

    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(),EnterpriseDetailActivity.class);
            intent.putExtra("id",list.get(position-1).getId());
            startActivity(intent);
        }
    }

    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.btnsearch:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), EnterpriseFindActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
    // 网络加载进度条
    public void showProgressDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText("正在加载，请稍候...");
        progressDialog = new Dialog(getActivity(), R.style.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();
    }

    public void dismisProgressDialog() {
        if (progressDialog == null) {
            return;
        } else {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;

            }
        }
    }

    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 9:
                    dismisProgressDialog();
                    Toast.makeText(getActivity(), "服务器异常，获取数据失败",
                            Toast.LENGTH_LONG).show();
                    break;
                case 10: // 没有网络连接
                    dismisProgressDialog();
                    Toast.makeText(getActivity(), "当前没有网络连接", Toast.LENGTH_LONG)
                            .show();
                    break;
                case 11: // 获取数据失败
                    dismisProgressDialog();
                    Toast.makeText(getActivity(), "服务器异常，获取数据失败",
                            Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };
}
