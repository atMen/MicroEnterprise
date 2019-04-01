package com.spring.chanba.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.dialog.basedialog.Effectstype;
import com.spring.chanba.dialog.basedialog.effects.BaseEffects;
import com.spring.chanba.utils.Utils;

/**
 * Loading加载
 */
public class LoadingDialog extends Dialog implements DialogInterface {
    private RelativeLayout relativeMain;
    private TextView tvMessage;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private boolean isCancelable = true;
    private static LoadingDialog instance;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_untran);
        initView(context);
    }

    public static LoadingDialog getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (LoadingDialog.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new LoadingDialog(context);
                }
            }
        }
        mContext = context;
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams p = getWindow().getAttributes();  // 获取对话框当前的参数值
        p.width = (int) (Utils.getWidth(mContext) * 0.8);            // 宽度设置为屏幕的0.8
        p.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(p);
    }

    private void initView(Context context) {
        view = View.inflate(context, R.layout.cb_dialog_loading, null);
        relativeMain = (RelativeLayout) view.findViewById(R.id.relative_loading);
        tvMessage = (TextView) view.findViewById(R.id.tv_loading_content);
        setContentView(view);
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (type == null) {
                    type = Effectstype.Slidetop;
                }
                start(type);
            }
        });
    }

    public LoadingDialog setMessage(int textResId) {
        tvMessage.setText(textResId);
        return this;
    }

    public LoadingDialog setMessage(CharSequence msg) {
        tvMessage.setText(msg);
        return this;
    }

    public LoadingDialog setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public LoadingDialog setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public LoadingDialog isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public LoadingDialog isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(relativeMain);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
