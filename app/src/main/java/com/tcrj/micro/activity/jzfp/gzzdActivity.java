package com.tcrj.micro.activity.jzfp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.micro.R;
import com.tcrj.micro.activity.HotListActivity;
import com.tcrj.micro.activity.left.LeftDetailActivity;
import com.tcrj.micro.application.BaseActivity;

public class gzzdActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_zl;
    private LinearLayout ll_ad;
    TextView txtTitle;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzzd);
        initView();
    }

    @Override
    public void initView() {
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        ll_zl = findViewById(R.id.ll_zl);
        ll_ad = findViewById(R.id.ll_ad);


        ll_zl.setOnClickListener(this);
        ll_ad.setOnClickListener(this);
        txtTitle.setText("工作制度");
        btnback.setOnClickListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this,LeftDetailActivity.class);

        switch (v.getId()){

            case R.id.btnback:
                finish();
                break;

            case R.id.ll_zl:

                intent.putExtra("id","FZVzYn");
                startActivity(intent);
                break;

            case R.id.ll_ad:
                intent.putExtra("id","QnAJRj");
                startActivity(intent);
                break;

            default:
                break;
        }


    }
}
