package com.tcrj.micro.activity.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.micro.R;


/**
 * Created by leict on 2018/4/2.
 */

public abstract class BaseFragment extends Fragment {



    protected View mRootView;
    private Dialog progressDialog;


    protected static Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = createView(inflater, container, savedInstanceState);
        }

        mContext = mRootView.getContext();

        return mRootView;
    }

    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData();
    }




    /**
     * 绑定布局
     * @return
     */
    protected abstract int  setLayout();

    /**
     * 初始化组件
     */
    protected abstract void setView();

    /**
     * 设置数据等逻辑代码
     */
    protected abstract void setData();







    protected void T(String s){
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    /**
     * Intent跳转
     * @param context
     * @param clazz
     */
    protected void toClass(Context context, Class clazz){
        toClass(context,clazz,null);
    }

    /**
     * Intent带值跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    protected void toClass(Context context, Class clazz, Bundle bundle){

        Intent intent = new Intent(context,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

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
    public void showProgressDialog(String s) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText(s);
        progressDialog = new Dialog(mContext, R.style.loading_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        progressDialog.show();

    }

}
