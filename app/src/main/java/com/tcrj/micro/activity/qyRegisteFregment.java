package com.tcrj.micro.activity;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.VolleyUtil;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CountDownButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by leict on 2018/7/2.
 */

public class qyRegisteFregment extends Fragment implements View.OnClickListener {

    private Dialog progressDialog;
    private View fragmetView;
    private EditText edt_name;
    private EditText edt_xydm;
    private EditText edt_sfz;
    private EditText edt_phone;
    private EditText edt_dxyzm;
    private EditText edt_Password;
    private EditText edt_Password2;
    private CountDownButton countDownButton;
    private Button btn_true;
    private String registrationID;

    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: //获取数据失败
                    dismisProgressDialog();
                    Toast.makeText(getContext(), "服务器异常，获取数据失败", Toast.LENGTH_LONG).show();
                    break;
                case 1: // 没有网络连接
                    dismisProgressDialog();
                    Toast.makeText(getContext(), "当前没有网络连接", Toast.LENGTH_LONG)
                            .show();
                    break;

                default:
                    break;
            }

        }

    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.registe_qy_fregment, container, false);
        initView();

        return fragmetView;

    }

    public void initView() {

        registrationID = ACache.get(getContext()).getAsString("RegistrationID");

        if(registrationID == null){
            registrationID = JPushInterface.getRegistrationID(getContext());
        }


        edt_name = fragmetView.findViewById(R.id.edt_name);
        edt_xydm = fragmetView.findViewById(R.id.edt_xydm);
        edt_sfz = fragmetView.findViewById(R.id.edt_sfz);
        edt_phone = fragmetView.findViewById(R.id.edt_phone);
        edt_dxyzm = fragmetView.findViewById(R.id.edt_dxyzm);
        edt_Password = fragmetView.findViewById(R.id.edt_Password);
        edt_Password2 = fragmetView.findViewById(R.id.edt_Password2);
        btn_true = fragmetView.findViewById(R.id.btn_true);

        btn_true.setOnClickListener(this);
        countDownButton = fragmetView.findViewById(R.id.timeButton);
        //设置Button点击事件触发倒计时
        countDownButton.setOnClickListener(this);
    }


    @Override
    public void onDestroy() {
        isfinish = true;
        if (!countDownButton.isFinish()) {
            countDownButton.cancel();
        }
        super.onDestroy();
    }


    private String token = "";
    private void qyRegiste(String namae, String xydm, String sfz, String phone, String dxyzm, String password) {

        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(getContext(), handler);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        params.put("cname", namae);
        params.put("identity", "");//身份证号
        params.put("mobile", phone);//手机号
        params.put("verifyCode", dxyzm);//短信验证码
        params.put("password", password);//密码

        params.put("registrationType", "android");//APP设备类型(android，ios)，推送消息使用
        params.put("registrationID", registrationID);//APP设备ID，推送消息使用

        params.put("type", 2);//注册类型 1.个人,2.个私企业,3.商务秘书公司
        params.put("tyshxydm", xydm);//企业：个私企统一信用代码
        params.put("frIdentity", sfz);//企业：法人身份证号

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {

                if(!isfinish){
                    dismisProgressDialog();
                    if (JsonParse.getMsgByKey(jsonObject, "errorCode").equals("SUCCESS")) {

                        Toast.makeText(MyApplication.getInstance(), "注册成功", Toast.LENGTH_SHORT).show();
                        //根据注册进入点，跳转到对应模块

                        getActivity().finish();


                    } else {
                        Toast.makeText(MyApplication.getInstance(), JsonParse.getMsgByKey(jsonObject, "data"), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
//              handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.registe, params, callback2);
    }

    private void getdxyam(String phone) {

        VolleyUtil volleyUtil = new VolleyUtil(getContext(), handler);
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("mobile", phone);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.d("aa", jsonObject.toString());

                if(!isfinish) {
                    if (JsonParse.getMsgByKey(jsonObject, "errorCode").equals("SUCCESS")) {
                        Toast.makeText(MyApplication.getInstance(), "验证码已发送，请注意查收", Toast.LENGTH_SHORT).show();

                        token = JsonParse.getMsgByKey(jsonObject, "data");
                    } else {
                        Toast.makeText(MyApplication.getInstance(), "验证码获取失败", Toast.LENGTH_SHORT).show();
                        if (!countDownButton.isFinish()) {
                            countDownButton.cancel();
                        }

                    }
                }
            }

            @Override
            public void onFailed(String result) {
                if (!countDownButton.isFinish()) {
                    countDownButton.cancel();
                }

                if(!isfinish){
                    Toast.makeText(MyApplication.getInstance(), "验证码获取失败", Toast.LENGTH_SHORT).show();
                }

//              handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.getdxyzm, params, callback2);
    }

    private boolean isfinish;
    //取消网络进度条
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

    // 网络加载进度条
    public void showProgressDialog() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText("正在加载，请稍候...");
        progressDialog = new Dialog(getContext(), R.style.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_true:

                String namae = edt_name.getText().toString().trim();
                String sfz = edt_sfz.getText().toString().trim();
                String phone = edt_phone.getText().toString().trim();
                String dxyzm = edt_dxyzm.getText().toString().trim();
                String Password = edt_Password.getText().toString().trim();
                String Password2 = edt_Password2.getText().toString().trim();
                String xydm = edt_xydm.getText().toString().trim();

                if("".equals(namae)){

                    Toast.makeText(getContext(), "请输入企业名称", Toast.LENGTH_SHORT).show();
                    return;
                }

                if("".equals(xydm) || xydm.length() != 10){

                    Toast.makeText(getContext(), "社会统一信用代码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                if("".equals(sfz) || sfz.length() != 15){

                    Toast.makeText(getContext(), "法人身份证号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                if("".equals(phone) || phone.length() != 11){

                    Toast.makeText(getContext(), "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                if("".equals(dxyzm)){

                    Toast.makeText(getContext(), "请输入短信验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if("".equals(Password) || "".equals(Password2)){

                    Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Password.equals(Password2)){

                    Toast.makeText(getContext(), "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                qyRegiste(namae,xydm,sfz,phone,dxyzm,Password);
                break;

            case R.id.timeButton:

                String numble = edt_phone.getText().toString().trim();
                if("".equals(numble) || numble.length() != 11){
                    Toast.makeText(getContext(), "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (countDownButton.isFinish()) {

                    getdxyam(numble);

                    //发送验证码请求成功后调用
                    countDownButton.start();


                }

                break;

            default:
                break;
        }
    }
}
