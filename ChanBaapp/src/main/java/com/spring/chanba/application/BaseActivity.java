package com.spring.chanba.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.spring.chanba.R;
import com.spring.chanba.dialog.LoadingDialog;
import com.spring.chanba.dialog.basedialog.Effectstype;
import com.spring.chanba.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {
    public LoadingDialog loading;

    public abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackControlUtil.add(this);
    }

    /**
     * 加载进度条
     *
     * @param message
     */
    public void showProgressDialog(String message) {
        loading = LoadingDialog.getInstance(this);
        loading.setMessage(message)
                .setDuration(700)
                .setEffect(Effectstype.Fadein)
                .show();
    }

    public void showProgressDialog() {
        loading = LoadingDialog.getInstance(this);
        loading.setMessage("正在加载...")
                .setDuration(700)
                .setEffect(Effectstype.Fadein)
                .show();
    }

    /**
     * 关闭进度条
     */
    public void dismisProgressDialog() {
        if (loading == null) {
            return;
        } else {
            if (loading.isShowing()) {
                loading.dismiss();
                loading = null;
            }
        }
    }

    /**
     * 根据传入的类(class)打开指定的activity
     *
     * @param pClass
     */
    protected void openNewActivity(Class<?> pClass) {
        Intent _Intent = new Intent();
        _Intent.setClass(this, pClass);
        startActivity(_Intent);
    }

    /**
     * Toast提示
     *
     * @param text
     */
    public void displayToast(String text) {
        if (text == null)
            return;
        View view = getLayoutInflater().inflate(R.layout.myself_toast, null);
        TextView message = (TextView) view.findViewById(R.id.chapterName);
        RelativeLayout relativeToast = (RelativeLayout) view.findViewById(R.id.relative_toast);
        relativeToast.getBackground().mutate().setAlpha(125);
        message.setText(text);
        Toast start = new Toast(this);
        int get_height = Utils.getHeight(this) / 3;
        start.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, get_height);
        start.setDuration(Toast.LENGTH_SHORT);
        start.setView(view);
        start.show();
    }

    public static class ActivityStackControlUtil {
        private static List<Activity> activityList = new ArrayList<Activity>();

        public static int getCounter() {
            return activityList.size();
        }

        public static void remove(Activity activity) {
            activityList.remove(activity);
        }

        public static void add(Activity activity) {
            activityList.add(activity);
        }

        public static void finishProgram() {
            for (Activity activity : activityList) {
                activity.finish();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        public static void finishAllActivityWithout(Activity withoutActivity) {

            for (int index = activityList.size() - 1; index >= 0; index--) {
                if (withoutActivity != activityList.get(index)) {
                    activityList.get(index).finish();
                    activityList.remove(index);
                }
            }
        }
    }
}
