package com.tcrj.micro.activity.zxzp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.activity.jzfp.YqfpActivity;
import com.tcrj.micro.adpater.JybjAdapter;
import com.tcrj.micro.adpater.cityAdapter;
import com.tcrj.micro.adpater.gzjlAdapter;
import com.tcrj.micro.adpater.qzyxAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.JlInfo;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.grzpListInfo;
import com.tcrj.micro.entity.zcgsInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QyzpItemInfoActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private RecyclerView mRecyclerView3;

    private TextView name;
    private TextView sex;
    private TextView birthday;
    private TextView educationBackground;
    private TextView politicsFace;
    private TextView marry;
    private TextView resumeName;
    private TextView phone;
    private TextView eml;
    private MyTextViewZH txtTitle;
    private Button btn_ok;
    private Button btn_no;
    private Button btn_yq;
    private ImageView btnback;


    private MyOkHttp mMyOkhttp;
    private qzyxAdapter detailAdapter;//求职意向
    private gzjlAdapter gzjladapter;//工作经历
    private JybjAdapter jybjAdapter;//教育背景

    private List<JlInfo.JobWillsBean> beanList;
    private List<JlInfo.ProjectExperiencesBean> gzjlList;
    private List<JlInfo.EducationBackgroundsBean> jybjList;

    String resumeId;
    String id;
    String token;
    String jobId;
    String enterpriseId;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qyzp_item_info);

        token = ACache.get(this).getAsString("token");
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        Bundle bundle = this.getIntent().getExtras();


        userId  = bundle.getString("userId");
        enterpriseId  = bundle.getString("enterpriseId");
        jobId  = bundle.getString("jobId");
        resumeId  = bundle.getString("ResumeId");
        id  = bundle.getString("ID");

        initView();
        getData();

    }


    @Override
    public void initView() {

        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        mRecyclerView = findViewById(R.id.rv_qzyx);
        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        birthday = findViewById(R.id.birthday);
        educationBackground = findViewById(R.id.educationBackground);
        politicsFace = findViewById(R.id.politicsFace);
        marry = findViewById(R.id.marry);
        resumeName = findViewById(R.id.resumeName);
        phone = findViewById(R.id.phone);
        eml = findViewById(R.id.eml);
        btn_ok = findViewById(R.id.btn_ok);
        btn_no = findViewById(R.id.btn_no);
        btn_yq = findViewById(R.id.btn_yq);

        mRecyclerView = findViewById(R.id.rv_qzyx);
        mRecyclerView2 = findViewById(R.id.rv_qzyx2);
        mRecyclerView3 = findViewById(R.id.rv_qzyx3);

        txtTitle.setText("简历详情");
        beanList = new ArrayList<>();
        gzjlList = new ArrayList<>();
        jybjList = new ArrayList<>();

        btn_yq.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        btnback.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(detailAdapter = new qzyxAdapter(beanList));

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView2.setAdapter(gzjladapter = new gzjlAdapter(gzjlList));

        mRecyclerView3.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView3.setNestedScrollingEnabled(false);
        mRecyclerView3.setAdapter(jybjAdapter = new JybjAdapter(jybjList));

        detailAdapter.setEnableLoadMore(false);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
    }

    @Override
    public void getData() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("resumeId", resumeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.resumeDetail_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo data) {

                        if("9999".equals(data.getErrorcode())) {

                            JlInfo  response = new Gson().fromJson(data.getData(), JlInfo.class);

                            setUserInfo(response);
                            detailAdapter.setNewData(response.getJobWills());
                            gzjladapter.setNewData(response.getProjectExperiences());
                            jybjAdapter.setNewData(response.getEducationBackgrounds());

                        }
                    }
                });

    }

    /**
     * "resume": {
     "birthday": "2019-04-30",
     "educationBackground": "硕士",
     "educationId": "11102",
     "email": "323323@QQ.com",
     "evaluate": "很好",
     "hobby": "读书、音乐、旅游",
     "id": "ff8080816a6c0fb1016a6c174aec0002",
     "marry": "未婚",
     "mobile": "17802902206",
     "optime": "2019-04-30 10:33:46",
     "politicsFace": "中共党员（含预备党员）",
     "politicsFaceId": "11001",
     "publish": "1",
     "resumeName": "小明简历一",
     "sex": "男",
     "skill": "擅长各类知识",
     "tradeId": "107010401",
     "tradeName": "客户服务总监",
     "userId": "4b20953949944054883c3c834a2d5ea3",
     "username": "小明"
     },
     * @param response
     */

    private void setUserInfo(JlInfo response) {
        JlInfo.ResumeBean resume = response.getResume();

        name.setText(resume.getUsername());
        sex.setText(resume.getSex()+" | ");
        birthday.setText(resume.getBirthday());
        educationBackground.setText(resume.getEducationBackground()+" | ");
        politicsFace.setText(resume.getPoliticsFace()+" | ");
        marry.setText(resume.getMarry());
        resumeName.setText(resume.getResumeName());

        phone.setText(resume.getMobile());
        eml.setText(resume.getEmail());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_ok:
                showNormalDialog("同意",1);
                break;

            case R.id.btn_no:
                showNormalDialog("拒绝",2);
                break;

            case R.id.btn_yq:
                //邀请面试
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(this);

                normalDialog.setTitle("面试邀请");
                normalDialog.setMessage("确定执行该操作?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                sendMsg();

                            }
                        });
                normalDialog.setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                // 显示
                normalDialog.show();
                break;

            case R.id.btnback:
                finish();
                break;

            default:
                break;
        }
    }

    //发送邀请推送消息
    public void sendMsg() {

        showProgressDialog();
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("token", token);
            jsonObject.put("enterpriseId", enterpriseId);
            jsonObject.put("jobId", jobId);
            jsonObject.put("userId", userId);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.sendmsyqApi)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(QyzpItemInfoActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        dismisProgressDialog();
                        Toast.makeText(QyzpItemInfoActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        String errorCode = response.getErrorcode();
                        if("9999".equals(errorCode)){
                            finish();
                        }else if("204".equals(errorCode)){
                            toLogin();
                        }


                    }
                });

    }
    private void toLogin(){
        ACache.get(this).clear();
        Intent intent = new Intent();
        intent.putExtra("openid",-2);
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);

    }


    private void showNormalDialog(String s, final int type){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);

        normalDialog.setTitle(s);
        normalDialog.setMessage("确定执行该操作?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sendMsg(type);

                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        // 显示
        normalDialog.show();
    }


    public void sendMsg(int type) {

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("id", id);
            jsonObject.put("optime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jsonObject.put("isSuitable", type);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.sp_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo data) {


                        Toast.makeText(QyzpItemInfoActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();

                        if("9999".equals(data.getErrorcode())) {

                            finish();
                            //发送消息，刷新招聘列表

                            EventBus.getDefault().post(new MessageEvent("",ApiConstants.qyzp));
                        }

                    }
                });

    }
}
