package com.tcrj.micro.jpush;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.spring.chanba.ui.MainActivity;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.activity.MainActivity1;
import com.tcrj.micro.activity.jrtz.jralInfoActivity;
import com.tcrj.micro.activity.jzfp.jzfpActivity;
import com.tcrj.micro.activity.left.LeftDetailActivity;
import com.tcrj.micro.activity.zxzp.QyzpItemInfoActivity;
import com.tcrj.micro.activity.zxzp.zwItemInfoActivity;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.until.ACache;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;



/**
 *
 * <!-- 邀请扶贫 -->
 <item type="FUPIN_COMPANY_PUSH">
 <!-- 职位发布消息推送 -->
 <item type="ZWFB_ZHAOPIN_PUSH">
 <!-- 面试邀请消息推送 -->
 <item type="MSYQ_ZHAOPIN_PUSH">
 <!-- 投递消息消息推送 -->
 <item type="TDXX_ZHAOPIN_PUSH">
 <!-- 政策推送 -->
 <item type="CATEGORY_INFO_PUSH">

 *
 * @author qndroid
 * @function 接收极光服务广播出来的推送消息，实现跳转逻辑
 */
public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = JPushReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[JPushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[JPushReceiver] 接收到推送下来的通知的ID: " + notifactionId);


        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {

            /**
             * 此处可以通过写一个方法，决定出要跳转到那些页面，一些细节的处理，可以通过是不是从推送过来的，去多一个分支去处理。
             * 1.应用未启动,------>启动主页----->不需要登陆信息类型，直接跳转到消息展示页面
             *                         ----->需要登陆信息类型，由于应用都未启动，肯定不存在已经登陆这种情况------>跳转到登陆页面
             *                                                                                                 ----->登陆完毕，跳转到信息展示页面。
             *                                                                                                 ----->取消登陆，返回首页。
             * 2.如果应用已经启动，------>不需要登陆的信息类型，直接跳转到信息展示页面。
             *                 ------>需要登陆的信息类型------>已经登陆----->直接跳转到信息展示页面。
             *                                      ------>未登陆------->则跳转到登陆页面
             *                                                                      ----->登陆完毕，跳转到信息展示页面。
             *                                                                      ----->取消登陆，回到首页。
             *
             * 3.startActivities(Intent[]);在推送中的妙用,注意startActivities在生命周期上的一个细节,
             * 前面的Activity是不会真正创建的，直到要到对应的页面
             * 4.如果为了复用，可以将极光推送封装到一个Manager类中,为外部提供init, setTag, setAlias,
             *
             * setNotificationCustom等一系列常用的方法。
             */
//          PushMessage pushMessage = (PushMessage) ResponseEntityToModule
//                    .parseJsonToModule(, PushMessage.class);
            Log.e("TAG","推送内容："+bundle.getString(JPushInterface.EXTRA_EXTRA));//附加字段内容
            String string = bundle.getString(JPushInterface.EXTRA_EXTRA);

            Gson mgson = new Gson();
            PushMessage pushMessage = mgson.fromJson(
                    string, PushMessage.class);

            /**
             * 如果应用已经启动(无论前台还是后台)
             */
            if (getCurrentTask(context)) {
                Intent pushIntent = new Intent();
                pushIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                pushIntent.putExtra("pushMessage", pushMessage);

                if(pushMessage.getType() == null){return;}

                switch (pushMessage.getType()){

                    /**
                     * 邀请扶贫
                     */
                    case "FUPIN_COMPANY_PUSH":

//                      EventBus.getDefault().post(new MessageEvent(pushMessage.getId(), ApiConstants.jpushfp));
                        pushIntent.setClass(context, jzfpActivity.class);
                        ACache.get(context).put("jpushId",pushMessage.getId());
//                      pushIntent.putExtra("id", pushMessage.getId());

                        break;

                    /**
                     * 职位发布消息推送
                     */
                    case "ZWFB_ZHAOPIN_PUSH":

                        pushIntent.setClass(context, zwItemInfoActivity.class);
                        pushIntent.putExtra("ID", pushMessage.getId());

                        break;

                    /**
                     * 面试邀请消息推送
                     */
                    case "MSYQ_ZHAOPIN_PUSH":
                        pushIntent.setClass(context, zwItemInfoActivity.class);
                        pushIntent.putExtra("ID", pushMessage.getId());
                        pushIntent.putExtra("type","1");
                        break;

                    /**
                     * 投递消息消息推送
                     */
                    case "TDXX_ZHAOPIN_PUSH":

                        pushIntent.setClass(context, QyzpItemInfoActivity.class);
                        pushIntent.putExtra("ID", pushMessage.getId());
                        pushIntent.putExtra("ResumeId", pushMessage.getResumeId());

                        break;

                    /**
                     * 金融案例
                     */
                    case "JRAL_PUSH":

                        pushIntent.setClass(context, jralInfoActivity.class);
                        pushIntent.putExtra("id", pushMessage.getId());
                        pushIntent.putExtra("type","0");
                        break;

                    /**
                     * 金融产品
                     */
                    case "JRCP_PUSH":

                        pushIntent.setClass(context, jralInfoActivity.class);
                        pushIntent.putExtra("id", pushMessage.getId());
                        pushIntent.putExtra("type","1");

                        break;

                    /**
                     * 政策推送
                     */
                    case "CATEGORY_INFO_PUSH":
                        pushIntent.setClass(context, LeftDetailActivity.class);
                        pushIntent.putExtra("id", pushMessage.getId());
                        break;

                    /**
                     * 通知推送
                     */
                    case "XWQY_COUNT_PUSH":
                        pushIntent.setClass(context, PushMessageActivity.class);
                        pushIntent.putExtra("messageContent", pushMessage.getMessageContent());
                        break;

                    default:
                        break;

                }


                context.startActivity(pushIntent);

            /**
            * 应用没有启动。。。
            */
            } else {
                /**
                 * 这里只分了两种类型，如果消息类型很多的话，用switch--case去匹配
                 */
                Intent mainIntent = new Intent(context, MainActivity1.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Intent loginIntent = new Intent();

                loginIntent.setClass(context, LoginActivity.class);



                if(pushMessage.getType() == null){return;}

                switch (pushMessage.getType()){

                    /**
                     * 邀请扶贫
                     */
                    case "FUPIN_COMPANY_PUSH":

                        loginIntent.setClass(context, jzfpActivity.class);
                        ACache.get(context).put("jpushId",pushMessage.getId());

                        break;

                    /**
                     * 职位发布消息推送
                     */
                    case "ZWFB_ZHAOPIN_PUSH":
                        loginIntent.setClass(context, zwItemInfoActivity.class);
                        loginIntent.putExtra("ID", pushMessage.getId());
                        break;

                    /**
                     * 面试邀请消息推送
                     */
                    case "MSYQ_ZHAOPIN_PUSH":
                        loginIntent.setClass(context, zwItemInfoActivity.class);
                        loginIntent.putExtra("ID", pushMessage.getId());
                        loginIntent.putExtra("type","1");
                        break;

                    /**
                     * 投递消息消息推送
                     */
                    case "TDXX_ZHAOPIN_PUSH":
                        loginIntent.setClass(context, QyzpItemInfoActivity.class);
                        loginIntent.putExtra("ID", pushMessage.getId());
                        loginIntent.putExtra("ResumeId", pushMessage.getResumeId());
                        break;

                    /**
                     * 政策推送
                     */
                    case "CATEGORY_INFO_PUSH":

                        loginIntent.setClass(context, LeftDetailActivity.class);
                        loginIntent.putExtra("id", pushMessage.getId());
                        break;

                    /**
                     * 金融案例
                     */
                    case "JRAL_PUSH":

                        loginIntent.setClass(context, jralInfoActivity.class);
                        loginIntent.putExtra("id", pushMessage.getId());
                        loginIntent.putExtra("type","0");
                        break;
                    /**
                     * 金融产品
                     */
                    case "JRCP_PUSH":

                        loginIntent.setClass(context, jralInfoActivity.class);
                        loginIntent.putExtra("id", pushMessage.getId());
                        loginIntent.putExtra("type","1");
                        break;
                    /**
                     * 通知推送
                     */
                    case "XWQY_COUNT_PUSH":
                        loginIntent.setClass(context, PushMessageActivity.class);
                        loginIntent.putExtra("messageContent", pushMessage.getMessageContent());
                        break;
                    default:
                        break;

                }

                context.startActivities(new Intent[]{mainIntent, loginIntent});

            }
        }else if(JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())){

            String registrationID = JPushInterface.getRegistrationID(context);
            Log.e(TAG, "[极光推送002] 获取到的RegistrationID: " + registrationID);
            //保存到本地
            ACache.get(context).put("RegistrationID",registrationID);
        }
    }

    //打印所有的 intent extra 数据
    @NonNull
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {

                    /**
                     * 先将JSON字符串转化为对象，再取其中的字段
                     */
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" + myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    /**
     * 这个是真正的获取指定包名的应用程序是否在运行(无论前台还是后台)
     *
     * @return
     */
    private boolean getCurrentTask(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> appProcessInfos = activityManager.getRunningTasks(50);
        for (RunningTaskInfo process : appProcessInfos) {

            if (process.baseActivity.getPackageName().equals(context.getPackageName())
                    || process.topActivity.getPackageName().equals(context.getPackageName())) {

                return true;
            }
        }
        return false;
    }

//    private void openNotification(Context context, Bundle bundle){
//        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//        String myValue = "";
//        try {
//            JSONObject extrasJson = new JSONObject(extras);
//            myValue = extrasJson.optString("myKey");
//        } catch (Exception e) {
//            Logger.w(TAG, "Unexpected: extras is not a valid json", e);
//            return;
//        }
//        if (TYPE_THIS.equals(myValue)) {
//            Intent mIntent = new Intent(context, ThisActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        } else if (TYPE_ANOTHER.equals(myValue)){
//            Intent mIntent = new Intent(context, AnotherActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        }
//    }
}