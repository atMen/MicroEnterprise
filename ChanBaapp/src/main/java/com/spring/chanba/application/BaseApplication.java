package com.spring.chanba.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.bean.WeChatUserInfoEntity;
import com.spring.chanba.utils.XMLName;

/**
 * 全局变量
 */
public class BaseApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Log.e("TAG","BaseApplication");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    /**
     * 设置微信信息
     *
     * @param entity
     */
    public static void setWechartInfo(WeChatUserInfoEntity entity) {

        if (entity == null) {
            return;
        }

        SharedPreferences share = context.getSharedPreferences(XMLName.NAME_WXUSER_INFO, 0);
        SharedPreferences.Editor e = share.edit();
        e.putString(XMLName.NAME_WXUSER_PERSONID, entity.getUnionid());
        e.putString(XMLName.NAME_WXUSER_PERSONNAME, entity.getNickname());
        e.putString(XMLName.NAME_WXUSER_PERSONIMAGE, entity.getHeadimgurl());
        e.commit();
    }

    /**
     * 存储用户信息
     *
     * @param entity
     */
    public static void setUserInfo(UserInfoEntity.DataBean entity) {
        if (entity == null) {
            return;
        }
        SharedPreferences share = context.getSharedPreferences(XMLName.USER_INFO_PERSON, 0);
        SharedPreferences.Editor e = share.edit();
        e.putString(XMLName.USER_INFO_PERSONID, entity.getId());
        e.commit();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfoEntity.DataBean getUserInfo() {
        UserInfoEntity.DataBean entity = new UserInfoEntity.DataBean();
        SharedPreferences share = context.getSharedPreferences(XMLName.USER_INFO_PERSON, 0);
        entity.setId(share.getString(XMLName.USER_INFO_PERSONID, ""));
        return entity;
    }

    /**
     * 获取微信信息
     *
     * @return
     */
    public static WeChatUserInfoEntity getUserInfoEntity() {
        WeChatUserInfoEntity bean = new WeChatUserInfoEntity();
        SharedPreferences share = context.getSharedPreferences(XMLName.NAME_WXUSER_INFO, 0);
        bean.setUnionid(share.getString(XMLName.NAME_WXUSER_PERSONID, ""));
        bean.setNickname(share.getString(XMLName.NAME_WXUSER_PERSONNAME, ""));
        bean.setHeadimgurl(share.getString(XMLName.NAME_WXUSER_PERSONIMAGE, ""));
        return bean;
    }
}
