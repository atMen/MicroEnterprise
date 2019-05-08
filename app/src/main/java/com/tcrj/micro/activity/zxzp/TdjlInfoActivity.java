package com.tcrj.micro.activity.zxzp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.RecruitInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class TdjlInfoActivity extends BaseActivity implements View.OnClickListener {

    private String id;

    private MyOkHttp mMyOkhttp;
    private TextView qyname;
    private TextView sfhs;
    private TextView hyname;
    private TextView sqtime;
    private TextView hftime;
    private TextView jlname;
    private TextView zwname;
    private TextView xz;
    private TextView content;
    private ImageView btnback;
    private MyTextViewZH txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdjl_info);

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");

        initView();
        getData();

    }

    @Override
    public void initView() {

        txtTitle = findViewById( R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        qyname = findViewById(R.id.qyname);
        sfhs = findViewById(R.id.sfhs);
        hyname = findViewById(R.id.hyname);
        sqtime = findViewById(R.id.sqtime);
        hftime = findViewById(R.id.hftime);
        jlname = findViewById(R.id.jlname);
        zwname = findViewById(R.id.zwname);
        xz = findViewById(R.id.xz);
        content = findViewById(R.id.content);

        txtTitle.setText("详情");
        btnback.setOnClickListener(this);

    }

    @Override
    public void getData() {

        showProgressDialog();
        Log.e("TAG","getData id:"+id);
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("id",id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.tdjl_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(TdjlInfoActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        dismisProgressDialog();
                        String errorCode = dataBean.getErrorcode();
                        if("9999".equals(errorCode)){

                            RecruitInfo response = new Gson().fromJson(dataBean.getData(), RecruitInfo.class);

                            setInfo(response);

                        }
                    }
                });


    }

    private String suitable;
    private void setInfo(RecruitInfo response) {
        String table = response.getIsSuitable();
        if("0".equals(table)){
            suitable = "待处理";
        }else if("1".equals(table)){
            suitable = "合适";
        }else if("2".equals(table)){
            suitable = "不合适";
        }


        qyname.setText(response.getCompanyName());
        sfhs.setText(suitable);
        hyname.setText(response.getIndustryName());
        sqtime.setText(response.getApplyDate());
        hftime.setText(response.getReplyDate());
        jlname.setText(response.getResumeName());
        zwname.setText(response.getJobName());
        xz.setText(response.getSalary());
        content.setText(response.getReplyContent());
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
