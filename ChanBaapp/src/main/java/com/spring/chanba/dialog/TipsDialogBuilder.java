package com.spring.chanba.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.dialog.basedialog.Effectstype;
import com.spring.chanba.dialog.basedialog.effects.BaseEffects;
import com.spring.chanba.utils.Utils;

/**
 * 提示Dialog
 */
public class TipsDialogBuilder extends Dialog implements DialogInterface {
    private LinearLayout relativeTips;
    private TextView tvTipsTitle;
    private TextView tvTipsContent;
    private Button btnTipsCancel;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private IMessageCallBack callBack = null;
    private static TipsDialogBuilder instance;

    public TipsDialogBuilder(Context context) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        initView(context);
    }

    public static TipsDialogBuilder getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (TipsDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new TipsDialogBuilder(context);
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
        p.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
    }

    private void initView(Context context) {
        view = View.inflate(context, R.layout.dialog_tips, null);
        relativeTips = (LinearLayout) view.findViewById(R.id.relative_tips);
        tvTipsTitle = (TextView) view.findViewById(R.id.tv_tips_title);
        tvTipsContent = (TextView) view.findViewById(R.id.tv_tips_content);
        btnTipsCancel = (Button) view.findViewById(R.id.btn_tips_cancel);
        this.setCanceledOnTouchOutside(false);
        setContentView(view);
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                if (type == null) {
                    type = Effectstype.Fadein;
                }
                start(type);
            }
        });
        btnTipsCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.setCancelListener();
                    dismiss();
                }
            }
        });
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(relativeTips);
    }

    public TipsDialogBuilder setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public TipsDialogBuilder setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public TipsDialogBuilder setTitles(int textResId) {
        tvTipsTitle.setText(textResId);
        return this;
    }

    public TipsDialogBuilder setTitles(CharSequence msg) {
        tvTipsTitle.setText(msg);
        return this;
    }

    public TipsDialogBuilder setContents(int textResId) {
        tvTipsContent.setText(textResId);
        return this;
    }

    public TipsDialogBuilder setContents(CharSequence msg) {
        tvTipsContent.setText(msg);
        return this;
    }

    public TipsDialogBuilder setSubmit(int textResId) {
        btnTipsCancel.setText(textResId);
        return this;
    }

    public TipsDialogBuilder setSubmit(CharSequence msg) {
        btnTipsCancel.setText(msg);
        return this;
    }

    public void setOnClickListener(IMessageCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IMessageCallBack {

        void setCancelListener();
    }
}