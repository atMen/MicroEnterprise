package com.spring.chanba.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.WeChatAccessEntity;
import com.spring.chanba.bean.WeChatUserInfoEntity;
import com.spring.chanba.utils.Constant;
import com.spring.chanba.utils.RetrofitUtil;
import com.spring.chanba.utils.Utils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private String TAG = WXEntryActivity.class.getSimpleName();
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, true);
        api.registerApp(Constant.WX_APP_ID);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void initView() {
        /**第三方开发者如果使用透明界面来实现WXEntryActivity，
         * 需要判断handleIntent的返回值，如果返回值为false，
         * 则说明入参不合法未被SDK处理，应finish当前透明界面，
         * 避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
         */
        boolean result = api.handleIntent(getIntent(), this);
        if (!result) {
            Log.i(TAG, "参数不合法，未被SDK处理，退出");
            finish();
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {
        System.out.print("------------:" + baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                final SendAuth.Resp authResp = (SendAuth.Resp) baseResp;
                getAccess_token(authResp.code);
                result = "登录成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "登录取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = "不支持错误";
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";
                break;
            default:
                result = "发送返回";
                finish();
                break;
        }
        displayToast(result);
    }

    /**
     * 微信登录
     *
     * @param code
     */
    public void getAccess_token(String code) {
        RetrofitUtil.initRequestURL().access_token(Constant.WX_APP_ID, Constant.WX_APP_SECRET, code, "authorization_code").enqueue(new Callback<WeChatAccessEntity>() {
            @Override
            public void onResponse(Call<WeChatAccessEntity> call, Response<WeChatAccessEntity> response) {
                WeChatAccessEntity result = response.body();
                getUserinfo(result.getAccess_token(), result.getOpenid());
            }

            @Override
            public void onFailure(Call<WeChatAccessEntity> call, Throwable t) {
                System.out.println(call);
            }
        });
    }

    /**
     * 获取微信的用户信息
     *
     * @param access_token
     * @param openid
     */
    public void getUserinfo(String access_token, String openid) {
        RetrofitUtil.initRequestURL().userinfo(access_token, openid, "zh_CN").enqueue(new Callback<WeChatUserInfoEntity>() {
            @Override
            public void onResponse(Call<WeChatUserInfoEntity> call, Response<WeChatUserInfoEntity> response) {
                WeChatUserInfoEntity result = response.body();
                String nickname = Utils.ignoreEmoji(result.getNickname());
                Intent intent = new Intent("wechat_login");
                intent.putExtra("openid", result.getOpenid());
                intent.putExtra("nickname", nickname);
                intent.putExtra("headimgurl", result.getHeadimgurl());
                intent.putExtra("unionid", result.getUnionid());
                BaseApplication.setWechartInfo(result);
                sendBroadcast(intent);
                finish();
            }

            @Override
            public void onFailure(Call<WeChatUserInfoEntity> call, Throwable t) {
                System.out.println(call);
            }
        });
    }
}
