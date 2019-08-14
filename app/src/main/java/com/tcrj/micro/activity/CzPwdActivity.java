package com.tcrj.micro.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.toolbox.VolleyUtil;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.view.CountDownButton;
import com.tcrj.micro.view.MyTextViewZH;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CzPwdActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private LinearLayout ll_one;
    private LinearLayout ll_two;
    private LinearLayout ll_three;
    private EditText edt_phone;
    private EditText edt_dxyzm;
    private EditText edt_Password;
    private EditText edt_Password2;
    private CountDownButton countDownButton;
    private Button btn_true;
    private RadioGroup radioGroup_sex_id;
    private RadioButton gr_id;
    private RadioButton qy_id;
    private RadioButton gsj_id;
    private MyTextViewZH txtTitle;
    private ImageView btnback;

    private int state = 0;
    private boolean isfinish;
    private String yzmtoken;

    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: //获取数据失败
                    dismisProgressDialog();
                    Toast.makeText(CzPwdActivity.this, "服务器异常，获取数据失败", Toast.LENGTH_LONG).show();
                    break;
                case 1: // 没有网络连接
                    dismisProgressDialog();
                    Toast.makeText(CzPwdActivity.this, "当前没有网络连接", Toast.LENGTH_LONG)
                            .show();
                    break;

                default:
                    break;
            }

        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cz_pwd);
        initView();
    }

    @Override
    public void initView() {

        type  = getIntent().getIntExtra("logintype",-1);

        btnback = findViewById(R.id.btnback);
        txtTitle = findViewById(R.id.txtTitle);
        ll_one = findViewById( R.id.ll_one);
        ll_two = findViewById( R.id.ll_two);
        ll_three = findViewById( R.id.ll_three);
        radioGroup_sex_id = findViewById(R.id.radioGroup_sex_id);
        gr_id = findViewById(R.id.gr_id);
        qy_id = findViewById(R.id.qy_id);
        gsj_id = findViewById(R.id.gsj_id);
        edt_phone = findViewById(R.id.edt_phone);
        edt_dxyzm = findViewById(R.id.edt_dxyzm);
        edt_Password = findViewById(R.id.edt_Password);
        edt_Password2 = findViewById(R.id.edt_Password2);
        btn_true = findViewById(R.id.btn_true);

        if(type == 1){
            gr_id.setChecked(true);
        }else if(type == 2){
            qy_id.setChecked(true);
        }else {
            gsj_id.setChecked(true);
        }
        txtTitle.setText("手机验证");
        countDownButton = findViewById(R.id.timeButton);
        //设置Button点击事件触发倒计时
        countDownButton.setOnClickListener(this);
        btn_true.setOnClickListener(this);
        radioGroup_sex_id.setOnCheckedChangeListener(this);
        btnback.setOnClickListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnback:

                finish();
                break;


            case R.id.btn_true:

                if(state == 0){


                    String phone = edt_phone.getText().toString().trim();
                    String dxyzm = edt_dxyzm.getText().toString().trim();

                    if("".equals(phone) || phone.length() != 11){

                        Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if("".equals(dxyzm)){

                        Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //手机验证
                    yzPhone(phone,dxyzm);


                }else if(state == 1){

                    String Password = edt_Password.getText().toString().trim();
                    String Password2 = edt_Password2.getText().toString().trim();

                    if("".equals(Password) || "".equals(Password2)){

                        Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(!Password.equals(Password2)){

                        Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //重置密码
                    czPwd(Password);

                }else if(state == 2){


                    //重置密码状态提示
                    finish();

                }

                break;

            case R.id.timeButton:

                String numble = edt_phone.getText().toString().trim();
                if("".equals(numble) || numble.length() != 11){
                    Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (countDownButton.isFinish()) {

                    //获取短信验证码
                    getdxyam(numble);
                    //发送验证码请求成功后调用
                    countDownButton.start();

                }

                break;

            default:
                break;
        }
    }


    //重置密码
    private void czPwd(String password) {

        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();


        params.put("token", phonedata);
        params.put("password", password);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {

                Toast.makeText(MyApplication.getInstance(), JsonParse.getMsgByKey(jsonObject, "message"), Toast.LENGTH_SHORT).show();
                dismisProgressDialog();
                if (JsonParse.getMsgByKey(jsonObject, "errorcode").equals("9999")) {

                    state++;
                    //显示第三阶段
                    txtTitle.setText("重置完成");
                    btn_true.setText("去登录");
                    ll_one.setVisibility(View.GONE);
                    ll_two.setVisibility(View.GONE);
                    ll_three.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
               Toast.makeText(MyApplication.getInstance(), "密码重置失败", Toast.LENGTH_SHORT).show();

            }
        };
        volleyUtil.getJsonDataFromServer(Constant.czpwd, params, callback2);

    }


    private String phonedata;
    //验证手机号
    private void yzPhone(String phone, String dxyzm) {

        showProgressDialog();
        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("type", type+"");
        params.put("mobile", phone);
        params.put("token", yzmtoken);
        params.put("verifyCode", dxyzm);

        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                Toast.makeText(MyApplication.getInstance(), JsonParse.getMsgByKey(jsonObject, "message"), Toast.LENGTH_SHORT).show();
                dismisProgressDialog();
                if (JsonParse.getMsgByKey(jsonObject, "errorcode").equals("9999")) {

                    phonedata = JsonParse.getMsgByKey(jsonObject, "data");
                    state++;
                    //显示第二阶段
                    txtTitle.setText("设置新密码");
                    ll_one.setVisibility(View.GONE);
                    ll_two.setVisibility(View.VISIBLE);
                    ll_three.setVisibility(View.GONE);

                } else {

                    if (!countDownButton.isFinish()) {
                        countDownButton.cancel();
                    }
                }
            }

            @Override
            public void onFailed(String result) {
                dismisProgressDialog();
                if (!countDownButton.isFinish()) {
                    countDownButton.cancel();
                }

                if(!isfinish){
                    Toast.makeText(MyApplication.getInstance(), "验证失败", Toast.LENGTH_SHORT).show();
                }

//              handler.sendEmptyMessage(11);
            }
        };
        volleyUtil.getJsonDataFromServer(Constant.yzphone, params, callback2);

    }



    //获取验证码
    private void getdxyam(String phone) {

        VolleyUtil volleyUtil = new VolleyUtil(this, handler);
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("mobile", phone);
        VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

            @Override
            public void onSuccess(JSONObject jsonObject) {

                if (JsonParse.getMsgByKey(jsonObject, "errorcode").equals("9999")) {
                    Toast.makeText(MyApplication.getInstance(), "验证码已发送，请注意查收", Toast.LENGTH_SHORT).show();

                    yzmtoken = JsonParse.getMsgByKey(jsonObject, "data");


                } else {
                    Toast.makeText(MyApplication.getInstance(), "验证码获取失败", Toast.LENGTH_SHORT).show();
                    if (!countDownButton.isFinish()) {
                        countDownButton.cancel();
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

    private int type = 1;
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


    @Override
    public void onDestroy() {
        isfinish = true;
        if (!countDownButton.isFinish()) {
            countDownButton.cancel();
        }
        super.onDestroy();
    }
}
