package com.tcrj.micro.jpush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcrj.micro.R;


/**
 * Created by qndroid on 16/11/19.
 *
 * @function 显示推送的消息界面
 */
public class PushMessageActivity extends Activity {


    private TextView tvtitle;
    private ImageView backBtn;
    private TextView messagecontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush_layout);
        initView();
        getData();
    }


    public void initView() {
        Intent intent = getIntent();
        String messageContent = intent.getStringExtra("messageContent");

        messagecontent = findViewById(R.id.messagecontent);
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);


        messagecontent.setText(messageContent);
        tvtitle.setText("推送消息");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void getData() {


    }


}
