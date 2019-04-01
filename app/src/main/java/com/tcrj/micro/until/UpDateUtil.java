package com.tcrj.micro.until;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


import com.google.gson.Gson;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by leict on 2019/1/23.
 */

public class UpDateUtil {

        public static void PgyUpdate(final Activity context) {

                PgyUpdateManager.register(context,
                        new UpdateManagerListener() {

                                @Override
                                public void onUpdateAvailable(final String result) {

                                        // 将新版本信息封装到AppBean中
                                        final AppBean appBean = getAppBeanFromString(result);
                                        new AlertDialog.Builder(context)
                                                .setTitle("更新")
                                                .setMessage(appBean.getReleaseNote())
                                                .setNegativeButton(
                                                        "确定",
                                                        new DialogInterface.OnClickListener() {

                                                                @Override
                                                                public void onClick(



                                                                        DialogInterface dialog,
                                                                        int which) {
                                                                        startDownloadTask(
                                                                                context,
                                                                                appBean.getDownloadURL());
                                                                }
                                                        }).show();
                                }

                                @Override
                                public void onNoUpdateAvailable() {



                                }
                        });
        }






}
