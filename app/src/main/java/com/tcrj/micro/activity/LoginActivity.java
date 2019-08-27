package com.tcrj.micro.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.VolleyUtil;
import com.google.gson.Gson;
import com.spring.chanba.ui.home.FinanceServiceActivity;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.LoginInfo;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.until.ACache;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    Button btnlogin;
    TextView tv_zc;
    TextView tv_cz;
    private EditText edt_Password;
    private EditText edt_username;
    private boolean isfinish;
    private Dialog progressDialog;
    private RadioGroup radioGroup_sex_id;
    TextView tv_dl;
    LinearLayout ll_rg;
    private int type = 1;
    private int openid = 0;
    TextView txtTitle;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openid = getIntent().getIntExtra("openid", -1);
        initView();
        getData();

    }

    public void initView() {
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        ll_rg = findViewById(R.id.ll_rg);
        tv_dl = findViewById(R.id.tv_dl);
        edt_Password = findViewById(R.id.edt_Password);
        edt_username = findViewById(R.id.edt_username);
        radioGroup_sex_id = findViewById(R.id.radioGroup_sex_id);
        tv_zc = findViewById(R.id.tv_zc);
        tv_cz = findViewById(R.id.tv_cz);

        btnlogin = findViewById(R.id.btn_login);

        //我要扶贫进入之后，限制进行企业登录
        if(openid == -2){
            type = 4;
            txtTitle.setText("工商局内部登录");
            ll_rg.setVisibility(View.GONE);
        }

        txtTitle.setText("登录");
        btnback.setOnClickListener(this);

        tv_zc.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        tv_cz.setOnClickListener(this);
        radioGroup_sex_id.setOnCheckedChangeListener(this);
    }


    public void getData() {

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnback:
                finish();
                break;

            case R.id.btn_login:

                String username = edt_username.getText().toString().trim();
                String Password = edt_Password.getText().toString().trim();

                if("".equals(username)){
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if("".equals(Password)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                toLogin(username,Password);

                break;

            case R.id.tv_zc:
                Intent intent1 = new Intent(this, RegisteActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_cz:
                Intent intent = new Intent(this, CzPwdActivity.class);
                intent.putExtra("logintype",type);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: //获取数据失败
                    dismisProgressDialog();
                    Toast.makeText(MyApplication.getInstance(), "服务器异常，获取数据失败", Toast.LENGTH_LONG).show();
                    break;
                case 1: // 没有网络连接
                    dismisProgressDialog();
                    Toast.makeText(MyApplication.getInstance(), "当前没有网络连接", Toast.LENGTH_LONG)
                            .show();
                    break;

                default:
                    break;
            }

        }

    };

    private String registrationID;
    private void toLogin(String username, String Password) {

        registrationID = ACache.get(this).getAsString("RegistrationID");

        if(registrationID == null){
            registrationID = JPushInterface.getRegistrationID(this);
        }

//        100d8559090f896886e
        Log.e("TAG","登录-registrationID:"+registrationID);

        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("mobile", username);
        params.put("password", Password);
        params.put("registrationType", "android");
        params.put("type", type);
        params.put("registrationID", registrationID);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                dismisProgressDialog();
                Log.d("aa", jsonObject.toString());
                if (!isfinish) {

                    if (JsonParse.getMsgByKey(jsonObject, "errorcode").equals("9999")) {

                        String  data = JsonParse.getMsgByKey(jsonObject,"data");

                        LoginInfo loginInfo = new Gson().fromJson(data, LoginInfo.class);


                        String token = loginInfo.getToken();
                        String username = loginInfo.getUser().getCname();


                        Log.e("TAG","token"+token);

                        open(token,username,loginInfo);
                        //保存用户信息


                    } else {
                        Toast.makeText(MyApplication.getInstance(), JsonParse.getMsgByKey(jsonObject, "message"), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                if(!isfinish){
                    Toast.makeText(MyApplication.getInstance(), result, Toast.LENGTH_SHORT).show();
                }

            }
        };
        volleyUtil.getJsonDataFromServer(Constant.login, params, callback2);
    }


    private static final int EVENTYPE = 001;

    private void open(String token, String username, LoginInfo loginInfo) {
        ACache.get(this).clear();
        //保存token
        ACache.get(this).put("LoginInfo",loginInfo);
        ACache.get(this).put("token",token);
        ACache.get(this).put("username",username);
        ACache.get(this).put("logintype",type+"");
        ACache.get(this).put("mobile",loginInfo.getUser().getMobile());

        EventBus.getDefault().post(new MessageEvent(username,EVENTYPE));

        //根据进入点位置，跳转到对应模块
        switch (openid){
            case 1:
                Intent intent1 = new Intent(this, FinanceServiceActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
        //销毁当前页面
        finish();
    }

    @Override
    public void onDestroy() {
        isfinish = true;
        super.onDestroy();
    }

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

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText("正在加载，请稍候...");
        progressDialog = new Dialog(this, R.style.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (group.getCheckedRadioButtonId()){

            case R.id.gr_id:

                type = 1;
                break;

            case R.id.qy_id:
                type = 2;
                break;

            case R.id.gsj_id:
                type = 4;
                break;



            default:
                break;
        }
    }
}
