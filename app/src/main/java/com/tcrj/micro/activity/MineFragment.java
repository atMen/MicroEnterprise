package com.tcrj.micro.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.newui.dialog.widget.NormalDialog;
import com.newui.explosion.ExplosionField;
import com.newui.pulltozoom.PullToZoomScrollViewEx;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.mine.AboutActivity;
import com.tcrj.micro.activity.zxzp.grzpActivity;
import com.tcrj.micro.activity.zxzp.qyzpActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.CheckInfo;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.until.UpDateUtil;
import com.tcrj.micro.view.MyTextViewQH;
import com.tcrj.micro.view.MyTextViewXH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class MineFragment extends Fragment {
    private View fragmetView;
    private TextView title_tv;
    private PullToZoomScrollViewEx scrollView;
    private LinearLayout clean_ly;
    private LinearLayout about_ly;
    private LinearLayout update_ly;
    private LinearLayout finish_ly;
    private LinearLayout grzp_ll;
    private LinearLayout qyzp_ll;
    private MyTextViewXH cleana;
    private MyTextViewXH rubbish;
    private MyTextViewXH packagename;
    private MyTextViewQH username;

    private LinearLayout ll_login;
    ExplosionField explosionField ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.mine_fragment, container, false);
        EventBus.getDefault().register(this);
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        initView();
        return fragmetView;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

            Log.e("TAG","onHiddenChanged");

        } else {

            String name = ACache.get(getContext()).getAsString("username");
            String token = ACache.get(getContext()).getAsString("token");
            if(name == null){
                username.setText("点击登录");
                finish_ly.setVisibility(View.GONE);
            }else {

                checkToken(name,token);

            }

        }
    }

    private MyOkHttp mMyOkhttp;
    private void checkToken(final String name, String token) {
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("token", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.checktoken_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<CheckInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }

                    @Override
                    public void onSuccess(int statusCode, CheckInfo response) {
//                      Toast.makeText(mContext, response.getMessage(), Toast.LENGTH_SHORT).show();

                        String errorCode = response.getErrorcode();
                        if("9999".equals(errorCode)){

                            username.setText(name);
                            finish_ly.setVisibility(View.VISIBLE);

                        }else {
                            ACache.get(getContext()).clear();
                            username.setText("点击登录");
                            finish_ly.setVisibility(View.GONE);
                        }

                    }
                });
    }

    public void initView() {
        loadViewForCode();
        explosionField = new ExplosionField(getActivity());
        scrollView = (PullToZoomScrollViewEx) fragmetView.findViewById(R.id.scroll_view);
        ll_login = fragmetView.findViewById(R.id.ll_login);
        title_tv = (TextView) fragmetView.findViewById(R.id.txtTitle);
        clean_ly = (LinearLayout) fragmetView.findViewById(R.id.clean_ly);
        about_ly = (LinearLayout) fragmetView.findViewById(R.id.about_ly);
        update_ly = (LinearLayout) fragmetView.findViewById(R.id.update_ly);
        packagename = (MyTextViewXH) fragmetView.findViewById(R.id.packagename);
        cleana = (MyTextViewXH) fragmetView.findViewById(R.id.cleana);
        rubbish = (MyTextViewXH) fragmetView.findViewById(R.id.rubbish);
        username = (MyTextViewQH) fragmetView.findViewById(R.id.tv_user_name);
        finish_ly = (LinearLayout) fragmetView.findViewById(R.id.finish_ly);
        grzp_ll = (LinearLayout) fragmetView.findViewById(R.id.grzp_ll);
        qyzp_ll = (LinearLayout) fragmetView.findViewById(R.id.qyzp_ll);


        String name = ACache.get(getContext()).getAsString("username");
        String token = ACache.get(getContext()).getAsString("token");
        if(name == null){
            username.setText("点击登录");
            finish_ly.setVisibility(View.GONE);
        }else {

            checkToken(name,token);

        }

        title_tv.setText("我的");

        try {
            PackageManager pm = getActivity().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getActivity().getPackageName(), 0);
            String versionName = pi.versionName;
            String versioncode = pi.versionCode+"";
            packagename.setText("V"+versionName);
            Random ra =new Random();
            rubbish.setText(ra.nextInt(20)+"kb");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        finish_ly.setOnClickListener(new OnClick());
        clean_ly.setOnClickListener(new OnClick());
        about_ly.setOnClickListener(new OnClick());
        ll_login.setOnClickListener(new OnClick());
        //explosionField.addListener(clean_ly);
        update_ly.setOnClickListener(new OnClick());
        grzp_ll.setOnClickListener(new OnClick());
        qyzp_ll.setOnClickListener(new OnClick());

    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) fragmetView.findViewById(R.id.scroll_view);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }

    class OnClick implements OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_login:
                    String token = ACache.get(getContext()).getAsString("token");
                    if(token == null){
                        Intent intent1 = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent1);
                    }

                    break;
                case R.id.clean_ly:
                    explosionField.explode(cleana);
                    rubbish.setText("0kb");
                    break;

                case R.id.finish_ly:
                    showNormalDialog();

                    break;

                case R.id.about_ly:
                    Intent intent = new Intent(getContext(), AboutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.update_ly:
//                    UpDateUtil.PgyUpdate(getActivity());

                    init();

//                    final NormalDialog dialog = new NormalDialog(getActivity());
//                    dialog
//                            .title("提示")
//                            .titleTextColor(getActivity().getResources().getColor(R.color.app_blue))
//                            .content("为您提供了新版本")//
//                            .style(NormalDialog.STYLE_TWO)//
//                            .titleTextSize(26)//
//                            .contentTextColor(getActivity().getResources().getColor(R.color.app_sub_text))
//                            .btnText("立即更新", "取消")
//                            .btnTextColor(getActivity().getResources().getColor(R.color.app_blue), getActivity().getResources().getColor(R.color.app_sub_text))
//                            .dividerColor(getActivity().getResources().getColor(R.color.app_blue))
//                            .show();
                    break;

                case R.id.grzp_ll:
                    Intent grzpintent = new Intent(getContext(), qyzpActivity.class);
                    startActivity(grzpintent);

                    break;

                case R.id.qyzp_ll:
                    Intent qyzpintent = new Intent(getContext(), grzpActivity.class);
                    startActivity(qyzpintent);

                    break;

                default:
                    break;
            }
        }
    }

    private void init() {

        showProgressDialog();
        PgyUpdateManager.register(getActivity(),
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        dismisProgressDialog();
                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(getActivity())
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
                                                        getActivity(),
                                                        appBean.getDownloadURL());
                                            }
                                        }).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                        dismisProgressDialog();
                        Toast.makeText(getActivity(), "当前为最新版本", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private Dialog progressDialog;

    //取消网络进度条
    public void dismisProgressDialog() {
        if (progressDialog == null) {
            return;
        } else {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        }
    }


    // 网络加载进度条
    public void showProgressDialog() {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText("正在检测更新...");
        progressDialog = new Dialog(getActivity(), R.style.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Log.e("TAG","Event ");
        switch (messageEvent.getType()){

            case 001:

                Log.e("TAG","messageEvent.getMessage():"+messageEvent.getMessage());
                username.setText(messageEvent.getMessage());
                finish_ly.setVisibility(View.VISIBLE);
                break;


            default:
                break;
        }

    }

    private void showNormalDialog(){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getContext());

        normalDialog.setTitle("退出登录");
        normalDialog.setMessage("确定退出登录?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        ACache.get(getContext()).clear();
                        username.setText("点击登录");
                        finish_ly.setVisibility(View.GONE);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        // 显示
        normalDialog.show();
    }

}
