package com.tcrj.micro.activity.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.micro.R;
import com.tcrj.micro.activity.zxzp.qyzpActivity;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.view.MyTextViewXH;

public class EntrepreneurshipActivity extends BaseActivity implements View.OnClickListener {
    TextView txtTitle;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye);
        initView();
    }

    @Override
    public void initView() {

        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        txtTitle.setText("大学生创业就业");
        btnback.setOnClickListener(this);

        findViewById(R.id.cy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(EntrepreneurshipActivity.this, ChuangyeActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.jy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent grzpintent = new Intent(EntrepreneurshipActivity.this, qyzpActivity.class);
                startActivity(grzpintent);
            }
        });



    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
