package com.tcrj.micro.activity.jzfp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.micro.R;
import com.tcrj.micro.activity.left.LeftDetailActivity;
import com.tcrj.micro.application.BaseActivity;

public class ldjgActivity extends BaseActivity implements View.OnClickListener {



    TextView txtTitle;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldjg);
        initView();
    }

    @Override
    public void initView() {
        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);

        txtTitle.setText("领导机构");
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


            default:
                break;
        }


    }
}
