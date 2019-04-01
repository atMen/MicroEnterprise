package com.tcrj.micro.activity.support;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.micro.R;
import com.tcrj.micro.activity.enterprise.EnterpriseFindActivity;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.view.MyTextViewXH;

public class EnterpriseActivity extends BaseActivity {

    private MyTextViewXH cqy;
    private TextView tvtitle;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);
        initView();
    }

    @Override
    public void initView() {

        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        backBtn.setVisibility(View.VISIBLE);
        tvtitle.setText("企业库");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.cqy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(EnterpriseActivity.this, EnterpriseFindActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.cxyd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EnterpriseActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.csb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EnterpriseActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.cfr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EnterpriseActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void getData() {

    }
}
