package com.tcrj.micro.application;


import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.UserEntity;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsy.sdk.myokhttp.MyOkHttp;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    private static MyApplication mInstance = null;
    private int ScreenWidth = 0;
    private int ScreenHeight = 0;
    private float Density = 0;
    private UserEntity user;
    public static Typeface FZLTQH;
    public static Typeface FZLTZH;
    public static Typeface FZLTXH;
    private MyOkHttp mMyOkhttp;
    private IWXAPI wxapi;

    @Override
    public void onCreate() {

        super.onCreate();
        x.Ext.init(this);//Xutils初始化
        mInstance = this;
        initDate();
        initOkhttp();
        initJPush();
        regToWeiXin();
        ARouter.openLog();//开启日志
        ARouter.openDebug();//开启debug模式
        ARouter.init(mInstance);//初始化

    }

    public void regToWeiXin() {
        wxapi = WXAPIFactory.createWXAPI(this, Constant.appID);
        wxapi.registerApp(Constant.appID);
    }

    //初始化极光
    private void initJPush() {
        Log.e("TAG","jg");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    //初始化okhttp
    private void initOkhttp(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        mMyOkhttp = new MyOkHttp(okHttpClient);
    }

    public MyOkHttp getMyOkHttp() {
        return mMyOkhttp;
    }

    // 加载数据
    public void initDate() {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        ScreenWidth = dm.widthPixels;
        ScreenHeight = dm.heightPixels;
        FZLTQH = Typeface.createFromAsset(getAssets(), "fonts/FZLTQH.TTF");
        FZLTZH = Typeface.createFromAsset(getAssets(), "fonts/FZLTZH.TTF");
        FZLTXH = Typeface.createFromAsset(getAssets(), "fonts/FZLTXH.TTF");
    }

    public synchronized static MyApplication getInstance() {

        return mInstance;
    }

    public static MyApplication getmInstance() {

        return mInstance;
    }

    public static void setmInstance(MyApplication mInstance) {
        MyApplication.mInstance = mInstance;
    }

    public int getScreenWidth() {
        return ScreenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        ScreenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return ScreenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        ScreenHeight = screenHeight;
    }

    public float getDensity() {
        return Density;
    }

    public void setDensity(float density) {
        Density = density;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}