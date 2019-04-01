package com.tcrj.micro.application;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.micro.R;


public abstract class BaseActivity extends AppCompatActivity {
    private Dialog progressDialog;

    public abstract void initView();

    public abstract void getData();

    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: //获取数据失败
                    dismisProgressDialog();
                    Toast.makeText(BaseActivity.this, "服务器异常，获取数据失败", Toast.LENGTH_LONG).show();
                    break;
                case 1: // 没有网络连接
                    dismisProgressDialog();
                    Toast.makeText(BaseActivity.this, "当前没有网络连接", Toast.LENGTH_LONG)
                            .show();
                    break;

                default:
                    break;
            }

        }

    };


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

    protected void openActivity(Class<?> pClass, String id) {
        Intent _Intent = new Intent();
        _Intent.setClass(this, pClass);
        _Intent.putExtra("Id", id);
        startActivity(_Intent);
    }

    // 网络加载进度条
    public void showProgressDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText("正在加载，请稍候...");
        progressDialog = new Dialog(this, R.style.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();
    }

    protected void toClass(Context context, Class clazz, Bundle bundle){

        Intent intent = new Intent(context,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
