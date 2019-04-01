package com.tcrj.micro.activity.support;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tcrj.micro.R;
import com.tcrj.micro.application.BaseActivity;

public class ChuangyeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangye2);
        initView();
    }

    @Override
    public void initView() {

        findViewById(R.id.ll_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChuangyeActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getData() {

    }
}
