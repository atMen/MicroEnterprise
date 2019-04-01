package com.tcrj.micro.activity.jzfp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.activity.base.BaseFragment;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.CheckInfo;
import com.tcrj.micro.entity.fpdxInfo;
import com.tcrj.micro.until.ACache;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by leict on 2018/4/19.
 */

public class wyfpFregment extends BaseFragment implements View.OnClickListener {


    TextView  tv_sclc;
    TextView  tv_xslc;

    private FragmentManager fragmentManager;
    private wdfpFragment newsFragment;
    private fpdxFregment settingFragment;

    private String logintype;
    private LinearLayout ll_tab;
    String token;
    private MyOkHttp mMyOkhttp;
    private int checktoken = -2;

    @Override
    protected int setLayout() {
        return R.layout.wyfp_fregment;
    }

    @Override
    protected void setView() {
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        logintype = ACache.get(mContext).getAsString("logintype");

        ll_tab = mRootView.findViewById(R.id.ll_tab);
        tv_sclc = mRootView.findViewById(R.id.tv_sclc);
        tv_xslc = mRootView.findViewById(R.id.tv_xslc);

        if("1".equals(logintype)){
            ll_tab.setVisibility(View.GONE);
        }else {
            ll_tab.setVisibility(View.VISIBLE);
        }

        tv_xslc.setOnClickListener(this);
        tv_sclc.setOnClickListener(this);

        fragmentManager = getActivity().getSupportFragmentManager();
        setTabSelection(0);
    }

    private void checkToken(String token) {
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
                        Toast.makeText(mContext, error_msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, CheckInfo response) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();

                        String errorCode = response.getErrorcode();
                        if("9999".equals(errorCode)){

                            tv_xslc.setTextColor(getResources().getColor(R.color.blue));
                            tv_sclc.setTextColor(getResources().getColor(R.color.login_textcolor));
                            setTabSelection(1);


                        }else {
                            ACache.get(getContext()).clear();
                            Intent intent = new Intent();
                            intent.putExtra("openid",-2);
                            intent.setClass(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }

                    }
                });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_sclc:
                tv_sclc.setTextColor(getResources().getColor(R.color.blue));
                tv_xslc.setTextColor(getResources().getColor(R.color.login_textcolor));
                setTabSelection(0);
                break;

            case R.id.tv_xslc:

                //判断是否登录，token是否失效
                token = ACache.get(mContext).getAsString("token");
                checkToken(token);

                break;

            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (newsFragment == null) {
                    newsFragment = new wdfpFragment();
                    transaction.add(R.id.contentContainer, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }

                break;
            case 1:
                if (settingFragment == null) {
                    settingFragment = new fpdxFregment();
                    transaction.add(R.id.contentContainer, settingFragment);
                } else {
                    transaction.show(settingFragment);
                }
                break;

            default:
                break;

        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }


    }
    @Override
    protected void setData() {
//        getData(pageNum);
    }

}
