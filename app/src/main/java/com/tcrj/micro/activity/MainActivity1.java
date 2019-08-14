package com.tcrj.micro.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.newui.slidingmenu.BaseSlidingFragmentActivity;
import com.newui.slidingmenu.SlidingMenu;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tcrj.micro.PermissionListener;
import com.tcrj.micro.R;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.until.UpDateUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends BaseSlidingFragmentActivity {

    private SlidingMenu mSlidingMenu;
    private View view;
    private RadioGroup radioGroup;
    private RadioButton newsButton;
    private RadioButton supportButton;
    private RadioButton enterpriseButton;
    private RadioButton mineButton;

    private NewsFragment newFragment;
    private SupportFragment supportFragment;
    private EnterpriseFragment enterpriseFragment;
    private MineFragment mineFragment;
    private FragmentTransaction ft;
    public static MainActivity1 instance;
    private long exitTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        instance = this;
        setting();
        getPermissions();
        initView();
        getDate();
        initSlidingMenu();

    }

    private void setting() {
        NotificationManagerCompat notification = NotificationManagerCompat.from(this);
        boolean isEnabled = notification.areNotificationsEnabled();

        if(!isEnabled){

            //未打开通知
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("请在“通知”中打开通知权限")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                            Intent intent = new Intent();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("android.provider.extra.APP_PACKAGE", MainActivity1.this.getPackageName());
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                                intent.putExtra("app_package", MainActivity1.this.getPackageName());
                                intent.putExtra("app_uid", MainActivity1.this.getApplicationInfo().uid);
                            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:" + MainActivity1.this.getPackageName()));
                            } else if (Build.VERSION.SDK_INT >= 15) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", MainActivity1.this.getPackageName(), null));
                            }
                            startActivity(intent);
                        }

                    })
                    .create();
            alertDialog.show();
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

        }



    }


    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            };

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {//判断当前系统是不是Android6.0
            requestRuntimePermissions(PERMISSIONS_STORAGE, new PermissionListener() {
                @Override
                public void granted() {
                    //权限申请通过
                    Log.e("TAG","通过权限限制");
                    init();
                }

                @Override
                public void denied(List<String> deniedList) {
                    //权限申请未通过
                    for (String denied : deniedList) {
                        if (denied.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                            Toast.makeText(MainActivity1.this, "未获取到读写权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity1.this, "未获取到相关权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

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

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText("正在检测更新...");
        progressDialog = new Dialog(this, R.style.loading_dialog);
        progressDialog.setCancelable(true);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();

    }

    private void init() {

//        showProgressDialog();
//        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(this);
//        PgyUpdateManager.register(this,
//                new UpdateManagerListener() {
//
//                    @Override
//                    public void onUpdateAvailable(final String result) {
//
//                        dismisProgressDialog();
//
//                        // 将新版本信息封装到AppBean中
//                        final AppBean appBean = getAppBeanFromString(result);
//                        new AlertDialog.Builder(MainActivity1.this)
//                                .setTitle("更新")
//                                .setMessage(appBean.getReleaseNote())
//                                .setNegativeButton(
//                                        "确定",
//                                        new DialogInterface.OnClickListener() {
//
//                                            @Override
//                                            public void onClick(
//
//                                                    DialogInterface dialog,
//                                                    int which) {
//                                                startDownloadTask(
//                                                        MainActivity1.this,
//                                                        appBean.getDownloadURL());
//                                            }
//                                        }).show();
//                    }
//
//                    @Override
//                    public void onNoUpdateAvailable() {
//
//                        dismisProgressDialog();
//                    }
//                });
    }

    private void initView() {
        ft = getSupportFragmentManager().beginTransaction();
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        newsButton = (RadioButton) findViewById(R.id.tab_news);
        supportButton = (RadioButton) findViewById(R.id.tab_support);
        enterpriseButton = (RadioButton) findViewById(R.id.tab_enterprise);
        mineButton = (RadioButton) findViewById(R.id.tab_mine);
        newsButton.setTypeface(MyApplication.FZLTXH);
        supportButton.setTypeface(MyApplication.FZLTXH);
        enterpriseButton.setTypeface(MyApplication.FZLTXH);
        mineButton.setTypeface(MyApplication.FZLTXH);

        radioGroup.setOnCheckedChangeListener(new OnCheckedChange());
        newsButton.setChecked(true);
        newFragment = new NewsFragment();
        ft.add(R.id.frameContent, newFragment);
        ft.commit();
    }

    private void initSlidingMenu() {
        setBehindContentView(R.layout.main_left_layout);
        FragmentTransaction mFragementTransaction = getSupportFragmentManager().beginTransaction();
        Fragment mFrag = new LeftFragment();
        mFragementTransaction.replace(R.id.main_left_fragment, mFrag);
        mFragementTransaction.commit();
        // customize the SlidingMenu
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setShadowWidth(MyApplication.getInstance().getScreenWidth() / 40);
        mSlidingMenu.setBehindOffset(MyApplication.getInstance().getScreenWidth() / 6);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.setShadowDrawable(R.mipmap.shadow_left);
        mSlidingMenu.setSecondaryShadowDrawable(R.mipmap.shadow_right);
        mSlidingMenu.setFadeEnabled(true);
        mSlidingMenu.setBehindScrollScale(0.333f);

        /*mSlidingMenu.setSecondaryMenu(R.layout.main_right_layout);
        mFragementTransaction = getSupportFragmentManager().beginTransaction();
        Fragment mFrag2 = new RightFragment();
        mFragementTransaction.replace(R.id.main_right_fragment, mFrag2);
        mFragementTransaction.commit();*/
    }

    private void getDate(){

    }

    class OnCheckedChange implements OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            ft = getSupportFragmentManager().beginTransaction();
            hideFragments();
            if (R.id.tab_news == checkedId) {
                if (newFragment == null) {
                    newFragment = new NewsFragment();
                    ft.add(R.id.frameContent, newFragment);
                } else {
                    ft.show(newFragment);
                }
            } else if (R.id.tab_enterprise == checkedId) {
                if (supportFragment == null) {
                    supportFragment = new SupportFragment();
                    ft.add(R.id.frameContent, supportFragment);
                } else {
                    ft.show(supportFragment);
                }
            } else if (R.id.tab_support == checkedId) {
                if (enterpriseFragment == null) {
                    enterpriseFragment = new EnterpriseFragment();
                    ft.add(R.id.frameContent, enterpriseFragment);
                } else {
                    ft.show(enterpriseFragment);
                }
            } else if (R.id.tab_mine == checkedId) {
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.frameContent, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
            }
            ft.commit();
        }
    }


    private void hideFragments() {
        if (newFragment != null) {
            newFragment.onPause();
            ft.hide(newFragment);

        }
        if (supportFragment != null) {
            supportFragment.onPause();
            ft.hide(supportFragment);
        }
        if (enterpriseFragment != null) {
            enterpriseFragment.onPause();
            ft.hide(enterpriseFragment);
        }
        if (mineFragment != null) {
            mineFragment.onPause();
            ft.hide(mineFragment);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private static PermissionListener mListener;
    /**
     * 申请权限
     */
    public  void requestRuntimePermissions(
            String[] permissions, PermissionListener listener) {
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        // 遍历每一个申请的权限，把没有通过的权限放在集合中
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(MainActivity1.this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            } else {
                mListener.granted();
            }
        }
        // 申请权限
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity1.this,
                    permissionList.toArray(new String[permissionList.size()]), 1);
        }
    }

    /**
     * 申请后的处理
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            List<String> deniedList = new ArrayList<>();
            // 遍历所有申请的权限，把被拒绝的权限放入集合
            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    mListener.granted();
                } else {
                    deniedList.add(permissions[i]);
                }
            }
            if (!deniedList.isEmpty()) {
                mListener.denied(deniedList);
            }
        }
    }

}
