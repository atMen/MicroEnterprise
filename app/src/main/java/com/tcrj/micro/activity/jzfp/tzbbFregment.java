package com.tcrj.micro.activity.jzfp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.base.BaseFragment;
import com.tcrj.micro.adpater.zcgsAdapter;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.fpdxInfo;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.entity.zcgsInfo;
import com.tcrj.micro.view.BottomStyleDialog;
import com.tcrj.micro.view.MyGridView;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by leict on 2018/7/4.
 */

public class tzbbFregment extends BaseFragment implements AdapterView.OnItemClickListener, BottomStyleDialog.OnItemClickListener, View.OnClickListener {


    public MyGridView myGridView;
    LinearLayout layout_work_naturejob;
    LinearLayout layout_qx;
    TextView tv_work_naturejob;
    TextView tv_pp;
    TextView ck_qx;
    TextView sj;

    zcgsAdapter adapter1;

    List<zcgsInfo> zcgsInfo;

    private String sjname = null;

    private MyOkHttp mMyOkhttp;

    @Override
    protected int setLayout() {
        return R.layout.tzbb_fragment;
    }

    @Override
    protected void setView() {
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        myGridView = mRootView.findViewById(R.id.grid_sign);
        layout_work_naturejob = mRootView.findViewById(R.id.layout_work_naturejob);
        layout_qx = mRootView.findViewById(R.id.layout_qx);
        tv_work_naturejob = mRootView.findViewById(R.id.tv_work_naturejob);
        tv_pp = mRootView.findViewById(R.id.tv_pp);
        ck_qx = mRootView.findViewById(R.id.ck_qx);
        sj = mRootView.findViewById(R.id.sj);

        layout_work_naturejob.setOnClickListener(this);
        layout_qx.setOnClickListener(this);
        sj.setOnClickListener(this);
        ck_qx.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        getGirdData("");
        adapter1 = new zcgsAdapter(mContext);
        myGridView.setOnItemClickListener(this);


    }
    private void getGirdData(String code) {
        showProgressDialog("正在加载...");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("parentId", code);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.zcgs_listApi)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        Log.e("TAG","JSONArray"+response.toString());
                        dismisProgressDialog();

                        String fpjlListInfos = response.getData();

                        zcgsInfo = parseNoHeaderJArray(fpjlListInfos);

                        if(isdialog){
                            showDialog();
                        }else {
                            adapter1.setData(zcgsInfo);
                            myGridView.setAdapter(adapter1);
                        }

                    }
                });
    }

    private void showDialog() {
        dialog = new BottomStyleDialog(mContext,zcgsInfo);
        dialog.setOnItemClickListener(this);
        dialog.show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        zcgsInfo itemAtPosition = (zcgsInfo) parent.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putString("cityId",itemAtPosition.getId());
        bundle.putString("countyId","");
        toClass(mContext,CityTzInfoActivity.class,bundle);
    }

    BottomStyleDialog dialog;
    private boolean isqx = false;
    private boolean isdialog = false;
    private String cityCode = null;
    private String cityCode1 = null;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_work_naturejob:
                cityCode = null;
                isqx = true;
                isdialog = true;
                getGirdData("");
                break;

            case R.id.layout_qx:

                if(sjname != null){
                    isqx = false;
                    isdialog = true;
                    getGirdData(cityCode1);
                }else {
                    T("请选择市级");
                }

                break;
//
            case R.id.ck_qx:

                if(cityCode != null){
                    Bundle bundle = new Bundle();
                    bundle.putString("cityId",cityCode1);
                    bundle.putString("countyId",cityCode);
                    toClass(mContext,CountyTzInfoActivity.class,bundle);
                }else {
                    T("请选择区县");
                }
                break;

            case R.id.sj:
                Bundle bundle = new Bundle();
                bundle.putString("cityId","");
                bundle.putString("countyId","");
                toClass(mContext,jzfptzInfoActivity.class,bundle);
                break;

            default:

        }
    }

    @Override
    public void onItemClick(zcgsInfo listinfoBean) {
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }

        if(isqx){
            sjname = listinfoBean.getName();
            cityCode1 = listinfoBean.getId();
            cityCode = null;
            tv_work_naturejob.setText(listinfoBean.getName());
            tv_pp.setText("请选择区县");
        }else {
            cityCode = listinfoBean.getId();
            tv_pp.setText(listinfoBean.getName());
        }

    }



    private List<zcgsInfo> parseNoHeaderJArray(String strByJson) {

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();

        Gson gson = new Gson();
        List<zcgsInfo> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            zcgsInfo userBean = gson.fromJson(user, zcgsInfo.class);
            userBeanList.add(userBean);
        }
        return userBeanList;
    }
}
