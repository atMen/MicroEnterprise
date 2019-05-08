package com.tcrj.micro.activity.jrtz;

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
import com.tcrj.micro.entity.bankExample;
import com.tcrj.micro.entity.bankalInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class jralInfoActivity extends BaseActivity implements View.OnClickListener {


    private MyOkHttp mMyOkhttp;
    private String id;
    private String type;
    private HtmlTextView html_text;
    private TextView content_title;
    private TextView content_time;
    private MyTextViewZH txtTitle;
    private ImageView btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jral_info);
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");

        btnback = findViewById(R.id.btnback);
        html_text = findViewById(R.id.html_text);
        content_title = findViewById(R.id.content_title);
        content_time = findViewById(R.id.content_time);
        txtTitle = findViewById(R.id.txtTitle);

        btnback.setOnClickListener(this);

        txtTitle.setText("详情");
        getData();

    }

    @Override
    public void initView() {

    }

    @Override
    public void getData() {
        showProgressDialog();
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("id", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url("0".equals(type)? ApiConstants.jrztbankalinfo_Api : ApiConstants.jrztbankcpinfo_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        dismisProgressDialog();
                        Toast.makeText(jralInfoActivity.this, error_msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo dataBean) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();
                        dismisProgressDialog();
                        String errorCode = dataBean.getErrorcode();
                        if("9999".equals(errorCode)){

                            bankExample response = new Gson().fromJson(dataBean.getData(), bankExample.class);

                            setData(response);

                        }
                    }
                });
    }

    private void setData(bankExample response) {
        content_title.setText("0".equals(type)? response.getTitle() : response.getName());
        String createDate = response.getCreateDate();
        content_time.setText(createDate.substring(0,10));

        html_text.setHtml(response.getContent(),
                new HtmlHttpImageGetter(html_text));
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
