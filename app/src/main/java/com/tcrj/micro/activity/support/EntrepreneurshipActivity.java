package com.tcrj.micro.activity.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.view.MyTextViewXH;

public class EntrepreneurshipActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye);
        initView();
    }

    @Override
    public void initView() {

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
                Toast.makeText(EntrepreneurshipActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void getData() {

    }
}
