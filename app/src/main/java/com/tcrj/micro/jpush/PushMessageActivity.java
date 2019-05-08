package com.tcrj.micro.jpush;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush_layout);
        initView();
        getData();
    }


    public void initView() {

    }


    public void getData() {


    }


}
