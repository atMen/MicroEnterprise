package com.spring.chanba.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import com.spring.chanba.R;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.bean.WeChatUserInfoEntity;
import com.spring.chanba.contract.LoginContract;
import com.spring.chanba.presenter.LoginPresenter;
import com.spring.chanba.utils.Constant;
import com.spring.chanba.utils.Utils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;

/**
 * 欢迎界面：登录界面
 */
public class WelcomeActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;
    private ImageView imgWeChart;
    private Button btnSubmit;
    private static IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        imgWeChart = (ImageView) findViewById(R.id.img_wechart);
        btnSubmit = (Button) findViewById(R.id.btn_wechat_login);

        api = WXAPIFactory.createWXAPI(WelcomeActivity.this, Constant.WX_APP_ID, true);
        api.registerApp(Constant.WX_APP_ID);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "springstreet_wx_sso";
                api.sendReq(req);
            }
        });
    }

    /**
     * 请求数据
     */
    private void loadData(WeChatUserInfoEntity entity) {
        HashMap<String, String> map = new HashMap<>();
        map.put("wechatId", entity.getUnionid());
        map.put("headimgurl", entity.getHeadimgurl());
        map.put("nickname", entity.getNickname());
        presenter = new LoginPresenter(this);
        presenter.getData(map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WeChatUserInfoEntity entity = BaseApplication.getUserInfoEntity();
        Glide.with(this).load(entity.getHeadimgurl()).into(imgWeChart);
        if (entity != null) {
            if (!Utils.isStringEmpty(entity.getHeadimgurl()) && !Utils.isStringEmpty(entity.getNickname())) {
                loadData(entity);
            }
        }
        Log.i("************", entity.getUnionid());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            String headUrl = data.getStringExtra("headUrl");
            Glide.with(this).load(headUrl).into(imgWeChart);
            displayToast("登录成功");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 返回服务器失败消息
     *
     * @param message
     */
    @Override
    public void failedMessage(String message) {
        displayToast(message);
    }

    @Override
    public void initData(UserInfoEntity entity) {
        if (entity != null) {
            if (entity.getState() == 1) {
                BaseApplication.setUserInfo(entity.getData());
                displayToast(entity.getMessage());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                displayToast(entity.getMessage());
            }
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
