package com.tcrj.micro.activity.jrtz;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.NewsFragment;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.LoginInfo;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.backqyinfo;
import com.tcrj.micro.entity.bankExample;
import com.tcrj.micro.entity.bankalInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.zcgsInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JrcpSqActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final int EVENTYPE = 007;
    private LinearLayout llhyrd;
    private LinearLayout llzcjd;
    private TextView tvhyrd;
    private TextView tvzcjd;
    private View hyrd;
    private View zcjd;

    private LinearLayout ll_qy;
    private LinearLayout ll_gr;
    private MyTextViewZH txtTitle;
    private ImageView btnback;
    private Button btn_sq;

    //个人
    private EditText jrtz_address;
    private EditText jrtz_school;
    private EditText jrtz_education;
    private EditText jrtz_job;
    private EditText jrtz_jobadderss;
    private EditText jrtz_jobnum;
    private EditText jrtz_idnum;
    private EditText username;
    private RadioGroup radioGroup_sex_id;
    private RadioGroup radioGroup_gjj_id;


    //企业
    private TextView qyname;
    private EditText qy_telephone;
    private EditText qy_organizationcode;
    private EditText qy_legalperson;
    private EditText qy_cardnum;
    private EditText qy_contactname;
    private EditText qy_contactphone;
    private EditText qy_address;

    bankExample bankInfo;
    LoginInfo logininfo;

    MyOkHttp mMyOkhttp;

    private String sexType = "0";
    private String gjjType = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jrcp_sq);

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        initView();
    }

    @Override
    public void initView() {

        //获取产品信息
        Bundle bundle=getIntent().getExtras();
        bankInfo = (bankExample) bundle.getSerializable("backinfo");
        //获取用户信息/企业信息
        logininfo = (LoginInfo) ACache.get(this).getAsObject("LoginInfo");
        String type1 = logininfo.getUser().getType();
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        txtTitle.setText("完善信息");

        ll_qy = findViewById(R.id.ll_qy);
        ll_gr = findViewById(R.id.ll_gr);
        llhyrd = (LinearLayout) findViewById(R.id.ll_hyrd);
        llzcjd = (LinearLayout) findViewById(R.id.ll_zcjd);
        tvhyrd = (TextView) findViewById(R.id.tv_gr);
        tvzcjd = (TextView) findViewById(R.id.tv_qy);
        hyrd = (View) findViewById(R.id.gr);
        zcjd = (View) findViewById(R.id.qy);
        btn_sq = findViewById(R.id.btn_sq);

        radioGroup_sex_id =  findViewById(R.id.radioGroup_sex_id);
        radioGroup_gjj_id = findViewById(R.id.radioGroup_gjj_id);

        //个人
        jrtz_address = findViewById(R.id.jrtz_address);
        jrtz_school = findViewById(R.id.jrtz_school);
        jrtz_education = findViewById(R.id.jrtz_education);
        jrtz_job = findViewById(R.id.jrtz_job);
        jrtz_jobadderss = findViewById(R.id.jrtz_jobadderss);
        jrtz_jobnum = findViewById(R.id.jrtz_jobnum);
        jrtz_idnum = findViewById(R.id.jrtz_idnum);
        username = findViewById(R.id.name);

        if("1".equals(type1)){
            username.setText(logininfo.getUser().getCname());
        }


        //企业
        qyname = findViewById(R.id.qyname);
        qy_telephone = findViewById(R.id.qy_telephone);
        qy_organizationcode = findViewById(R.id.qy_organizationcode);
        qy_legalperson = findViewById(R.id.qy_legalperson);
        qy_cardnum = findViewById(R.id.qy_cardnum);
        qy_contactname = findViewById(R.id.qy_contactname);
        qy_contactphone = findViewById(R.id.qy_contactphone);
        qy_address = findViewById(R.id.qy_address);
        qyname.setText(logininfo.getUser().getCname());

        String type = logininfo.getUser().getType();

        ll_gr.setVisibility(View.VISIBLE);

        btn_sq.setOnClickListener(this);
        btnback.setOnClickListener(this);
        llhyrd.setOnClickListener(this);
        llzcjd.setOnClickListener(this);

        radioGroup_sex_id.setOnCheckedChangeListener(this);
        radioGroup_gjj_id.setOnCheckedChangeListener(this);


        //获取金融产品所属企业信息
        getBankinfo();

    }

    backqyinfo response;
    //获取产品所属金融企业信息
    private void getBankinfo() {

            showProgressDialog();
            JSONObject jsonObject = new JSONObject();

            try {

                jsonObject.put("id", bankInfo.getBankId());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            mMyOkhttp.post()
                    .url(ApiConstants.jrztbankqylist_Api)
                    .jsonParams(jsonObject.toString())
                    .enqueue(new GsonResponseHandler<fpStringInfo>() {
                        @Override
                        public void onFailure(int statusCode, String error_msg) {

                            dismisProgressDialog();
                            Toast.makeText(JrcpSqActivity.this, error_msg, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onSuccess(int statusCode, fpStringInfo dataBean) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                            dismisProgressDialog();
                            String errorCode = dataBean.getErrorcode();
                            if("9999".equals(errorCode)){

//                                response = new Gson().fromJson(dataBean.getData(), backqyinfo.class);
                                List<backqyinfo> backqyinfos = parseNoHeaderJArray(dataBean.getData());
                                response = backqyinfos.get(0);
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
    public void getData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll_hyrd:
                ll_qy.setVisibility(View.GONE);
                ll_gr.setVisibility(View.VISIBLE);
                hyrd.setVisibility(View.VISIBLE);
                tvhyrd.setTextColor(getResources().getColor(R.color.app_blue));
                zcjd.setVisibility(View.GONE);
                tvzcjd.setTextColor(getResources().getColor(R.color.black));

                break;

            case R.id.ll_zcjd:
                ll_qy.setVisibility(View.VISIBLE);
                ll_gr.setVisibility(View.GONE);

                hyrd.setVisibility(View.GONE);
                tvhyrd.setTextColor(getResources().getColor(R.color.black));
                zcjd.setVisibility(View.VISIBLE);
                tvzcjd.setTextColor(getResources().getColor(R.color.app_blue));

                break;

            case R.id.btnback:
                finish();
                break;

            //申请
            case R.id.btn_sq:

                    gr();



                break;

            default:
                break;
        }

    }

//    private void submitqyLoginInfo(String idnum,String address, String school, String education, String job,
//                                 String jobadderss, String jobnum) {
////        参数：bankId（企业id）,bankName（企业名称），loginId（登录账号），telphone（电话），organizationCode（组织机构代码），
////        legalPerson（法人姓名），cardNum（身份证号），contactName（联系人），contactPhone（联系人手机）
////        ，licenseFileImg（营业执照图片路径），address（地址）
//        showProgressDialog();
//        JSONObject jsonObject = new JSONObject();
//
//
//        try {
//
//            jsonObject.put("bankId", response.getId());
//            jsonObject.put("bankName", response.getName());
//            jsonObject.put("telphone", response.getPhone());
//            jsonObject.put("organizationCode", response.getCode());
//            jsonObject.put("legalPerson",response.getLegalName());
//            jsonObject.put("cardNum", response.getLeagalIdCard());
//            jsonObject.put("contactName", response.getLinkmanName());
//            jsonObject.put("contactPhone", response.getLinkmanPhone());
//            jsonObject.put("licenseFileImg", ApiConstants.BASEIMAGE+response.getLicenseUrl());
//            jsonObject.put("address", response.getAddress());
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        mMyOkhttp.post()
//                .url(ApiConstants.cb_qylogin)
//                .jsonParams(jsonObject.toString())
//                .enqueue(new GsonResponseHandler<fpStringInfo>() {
//                    @Override
//                    public void onFailure(int statusCode, String error_msg) {
//
//                        dismisProgressDialog();
//                        Toast.makeText(JrcpSqActivity.this, error_msg, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
//
//                        int errorCode = dataBean.getState();
//                        if(errorCode == 1){
//
//                            //金融产品
//                            submitCpInfo();
//                        }else {
//                            Toast.makeText(JrcpSqActivity.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }

    //同步个人信息
    private void gr() {

        String name = username.getText().toString().trim();
        String address = jrtz_address.getText().toString().trim();
        String school = jrtz_school.getText().toString().trim();
        String education = jrtz_education.getText().toString().trim();
        String job = jrtz_job.getText().toString().trim();
        String jobadderss = jrtz_jobadderss.getText().toString().trim();
        String jobnum = jrtz_jobnum.getText().toString().trim();
        String idnum = jrtz_idnum.getText().toString().trim();

        if("".equals(name)){
            Toast.makeText(this, "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }

        if("".equals(idnum)){
            Toast.makeText(this, "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }

        if("".equals(address)){
            Toast.makeText(this, "请输入住址", Toast.LENGTH_SHORT).show();
            return;
        }

        if("".equals(school)){
            Toast.makeText(this, "请输入毕业学校", Toast.LENGTH_SHORT).show();
            return;
        }

        if("".equals(education)){
            Toast.makeText(this, "请输入学历", Toast.LENGTH_SHORT).show();
            return;
        }

        if("".equals(job)){
            Toast.makeText(this, "请输入职业", Toast.LENGTH_SHORT).show();
            return;
        }

        if("".equals(jobadderss)){
            Toast.makeText(this, "请输入工作地点", Toast.LENGTH_SHORT).show();
            return;
        }

        if("".equals(jobnum)){
            Toast.makeText(this, "请输入工作年限", Toast.LENGTH_SHORT).show();
            return;
        }


        //上传登录信息
        submitLoginInfo(name,idnum,address,school,education,job,jobadderss,jobnum);
    }

    private void submitLoginInfo(String name, String idnum, String address, String school, String education, String job,
                                 String jobadderss, String jobnum) {
//                memberId（用户id）,
//        name（用户名），
//        IDNumber（身份证号），sex（性别 0:男 1：女），phone（联系电话），address（住址），university（毕业学校）
//        ，education（学历），
//        profession（职业），workplace（工作地点），workingLife（工作年限），isFund（是够缴纳住房公积金 0:否 1:是）
        showProgressDialog();
        JSONObject jsonObject = new JSONObject();
        LoginInfo.UserBean user = logininfo.getUser();
        try {
            //用户信息
            jsonObject.put("memberId", user.getId());
            jsonObject.put("name", name);
            jsonObject.put("IDNumber", idnum);
            jsonObject.put("sex", sexType);
            jsonObject.put("phone", user.getMobile());
            jsonObject.put("address", address);
            jsonObject.put("university", school);
            jsonObject.put("profession", job);
            jsonObject.put("workplace", jobadderss);
            jsonObject.put("workingLife", jobnum);
            jsonObject.put("isFund", gjjType);
            jsonObject.put("education", education);

            //产品信息
            jsonObject.put("bankId", bankInfo.getBankId());
            jsonObject.put("productId", bankInfo.getId());
            jsonObject.put("productName", bankInfo.getName());
            jsonObject.put("type", "1".equals(user.getType())? "0":"1");
            jsonObject.put("description", bankInfo.getIntroduction());
            jsonObject.put("productDetail", bankInfo.getContent());
            jsonObject.put("picPath", "".equals(bankInfo.getImg())? "" : ApiConstants.BASEIMAGE+bankInfo.getImg());

            //所属企业信息
            jsonObject.put("bankId", response.getId());
            jsonObject.put("bankName", response.getName());
            jsonObject.put("telphone", response.getPhone());
            jsonObject.put("organizationCode", response.getCode());
            jsonObject.put("legalPerson",response.getLegalName());
            jsonObject.put("cardNum", response.getLeagalIdCard());
            jsonObject.put("contactName", response.getLinkmanName());
            jsonObject.put("contactPhone", response.getLinkmanPhone());
            jsonObject.put("licenseFileImg", ApiConstants.BASEIMAGE+response.getLicenseUrl());
            jsonObject.put("address", response.getAddress());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.cb_login)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(JrcpSqActivity.this, error_msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {

                        dismisProgressDialog();
                        Toast.makeText(JrcpSqActivity.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
                        int errorCode = dataBean.getState();
                        if(errorCode == 1){

                            //通知申请按钮状态改变
//                            EventBus.getDefault().post(new MessageEvent("",EVENTYPE));
                            finish();
                        }
                    }
                });
    }


//    //上传产品信息
//    private void submitCpInfo() {
////        bankId（银行id），productId（产品ID），productName（产品名称），type（产品类型  供用户:0 供企业:1）
////        ，description（产品简介），productDetail（产品详情(富文本编辑器)），picPath（产品图片路径）
//        JSONObject jsonObject = new JSONObject();
//        String type = logininfo.getUser().getType();
//        try {
//
//            jsonObject.put("bankId", bankInfo.getBankId());
//            jsonObject.put("productId", bankInfo.getId());
//            jsonObject.put("productName", bankInfo.getName());
//            jsonObject.put("type", "1".equals(type)? "0":"1");
//            jsonObject.put("description", bankInfo.getIntroduction());
//            jsonObject.put("productDetail", bankInfo.getContent());
//            jsonObject.put("picPath", "".equals(bankInfo.getImg())? "" : ApiConstants.BASEIMAGE+bankInfo.getImg());
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        mMyOkhttp.post()
//                .url(ApiConstants.cb_addjrcp)
//                .jsonParams(jsonObject.toString())
//                .enqueue(new GsonResponseHandler<fpStringInfo>() {
//                    @Override
//                    public void onFailure(int statusCode, String error_msg) {
//
//                        dismisProgressDialog();
//                        Toast.makeText(JrcpSqActivity.this, error_msg, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
////                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
//                        int errorCode = dataBean.getState();
//                        if(errorCode == 1){
//
//                            //提交申请
//                            submit();
//                        }else {
//                            Toast.makeText(JrcpSqActivity.this, dataBean.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }




    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int checkedRadioButtonId = group.getCheckedRadioButtonId();
        switch (group.getId()){

            case R.id.radioGroup_sex_id:

                if(checkedRadioButtonId == R.id.gr_id){
                    sexType = "0";
                }else {
                    sexType = "1";
                }


                break;

            case R.id.radioGroup_gjj_id:
                if(checkedRadioButtonId == R.id.gb_y){
                    gjjType = "1";
                }else {
                    gjjType = "0";
                }
                break;


            default:
                break;
        }
    }
}
