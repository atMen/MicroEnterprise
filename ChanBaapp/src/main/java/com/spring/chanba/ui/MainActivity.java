package com.spring.chanba.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.spring.chanba.R;
import com.spring.chanba.application.BaseActivity;
import com.spring.chanba.dialog.MessageDialogBuilder;
import com.spring.chanba.ui.fragment.HomeFragment;
import com.spring.chanba.ui.fragment.InformationFragment;
import com.spring.chanba.ui.fragment.UserCenterFragment;

/**
 * 首页
 */


@Route(path = "/test/outerActivity")
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout frameContent;
    private FrameLayout tabHomepage;
    private FrameLayout tabInformation;
    private FrameLayout tabCenter;
    private ImageView imgHomepage;
    private ImageView imgCenter;
    private ImageView imgInformation;
    private TextView txtInformation;
    private TextView txtHomepage;
    private TextView txtCenter;
    private HomeFragment homeFragment;
    private InformationFragment inforFragment;
    private UserCenterFragment userFragment;
    private FragmentManager fragmentManager;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStatusBarFullTransparent();
        setContentView(R.layout.activity_main_cb);
        fragmentManager = getSupportFragmentManager();
        initView();
        setTabSelection(0);
    }

    /**
     * 获取页面控件
     */
    @Override
    public void initView() {
        tabHomepage = (FrameLayout) findViewById(R.id.tab_homepage);
        imgHomepage = (ImageView) findViewById(R.id.img_homepage);
        txtHomepage = (TextView) findViewById(R.id.txt_homepage);
        tabInformation = (FrameLayout) findViewById(R.id.tab_information);
        imgInformation = (ImageView) findViewById(R.id.img_information);
        txtInformation = (TextView) findViewById(R.id.txt_information);
        tabCenter = (FrameLayout) findViewById(R.id.tab_center);
        imgCenter = (ImageView) findViewById(R.id.img_center);
        txtCenter = (TextView) findViewById(R.id.txt_center);
        tabHomepage.setOnClickListener(this);
        tabInformation.setOnClickListener(this);
        tabCenter.setOnClickListener(this);
        final MessageDialogBuilder builder = MessageDialogBuilder.getInstance(this);
        builder.setTitles("温馨提示")
                .setContents("重新加载数据......")
                .setOnClickListener(new MessageDialogBuilder.IMessageCallBack() {
                    @Override
                    public void setSubmitListener() {
                        displayToast("敬请期待");
                    }

                    @Override
                    public void setCancelListener() {
                        builder.dismiss();
                    }
                });
        builder.show();
    }

    /**
     * 加载数据
     */
    private void updateStyle() {
        imgHomepage.setImageResource(R.mipmap.menu_home_normal);
        imgInformation.setImageResource(R.mipmap.menu_info_normal);
        imgCenter.setImageResource(R.mipmap.menu_center_normal);
        txtHomepage.setTextColor(Color.parseColor("#999999"));
        txtInformation.setTextColor(Color.parseColor("#999999"));
        txtCenter.setTextColor(Color.parseColor("#999999"));
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (inforFragment != null) {
            transaction.hide(inforFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        /**21表示5.0**/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            /**19表示4.4**/
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void setTabSelection(int index) {
        updateStyle();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                imgHomepage.setImageResource(R.mipmap.menu_home_select);
                txtHomepage.setTextColor(Color.parseColor("#35AEE3"));
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.frame_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                imgInformation.setImageResource(R.mipmap.menu_info_select);
                txtInformation.setTextColor(Color.parseColor("#35AEE3"));
                if (inforFragment == null) {
                    inforFragment = new InformationFragment();
                    transaction.add(R.id.frame_content, inforFragment);
                } else {
                    transaction.show(inforFragment);
                }
                break;
            case 2:
                imgCenter.setImageResource(R.mipmap.menu_center_select);
                txtCenter.setTextColor(Color.parseColor("#35AEE3"));
                if (userFragment == null) {
                    userFragment = new UserCenterFragment();
                    transaction.add(R.id.frame_content, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tab_homepage) {
            setTabSelection(0);

        } else if (i == R.id.tab_information) {
            setTabSelection(1);

        } else if (i == R.id.tab_center) {
            setTabSelection(2);

        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
////            if ((System.currentTimeMillis() - exitTime) > 2000) {
////                displayToast("再按一次退出程序");
////                exitTime = System.currentTimeMillis();
////            } else {
//                finish();
////                System.exit(0);
////            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                displayToast("再按一次退出程序");
//                exitTime = System.currentTimeMillis();
//            } else {
//                finish();
//                ActivityStackControlUtil.finishProgram();
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.dispatchKeyEvent(event);
//    }

//    @Override
//    protected void onDestroy() {
//        ActivityStackControlUtil.finishProgram();
//        super.onDestroy();
//    }
}
