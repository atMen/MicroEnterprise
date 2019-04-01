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
 * 弹出框
 */
public class MessageDialogBuilder extends Dialog implements DialogInterface {
    private LinearLayout relativeMessage;
    private TextView tvMessageTitle;
    private TextView tvMessageContent;
    private Button btnMessageSubmit;
    private Button btnMessageCancel;
    private View view;
    private int mDuration = -1;
    private Effectstype type = null;
    private static Context mContext;
    private IMessageCallBack callBack = null;
    private static MessageDialogBuilder instance;

    public MessageDialogBuilder(Context context) {
        super(context, R.style.dialog_untran);
        this.mContext = context;
        initView(context);
    }

    public static MessageDialogBuilder getInstance(Context context) {
        if (instance == null || !mContext.equals(context)) {
            synchronized (MessageDialogBuilder.class) {
                if (instance == null || !mContext.equals(context)) {
                    instance = new MessageDialogBuilder(context);
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
        view = View.inflate(context, R.layout.dialog_message, null);
        relativeMessage = (LinearLayout) view.findViewById(R.id.relative_message);
        tvMessageTitle = (TextView) view.findViewById(R.id.tv_message_title);
        tvMessageContent = (TextView) view.findViewById(R.id.tv_message_content);
        btnMessageSubmit = (Button) view.findViewById(R.id.btn_message_submit);
        btnMessageCancel = (Button) view.findViewById(R.id.btn_message_cancel);
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
        btnMessageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.setCancelListener();
                }
            }
        });
        btnMessageSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.setSubmitListener();
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
        animator.start(relativeMessage);
    }

    public MessageDialogBuilder setDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public MessageDialogBuilder setEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public MessageDialogBuilder setTitles(int textResId) {
        tvMessageTitle.setText(textResId);
        return this;
    }

    public MessageDialogBuilder setTitles(CharSequence msg) {
        tvMessageTitle.setText(msg);
        return this;
    }

    public MessageDialogBuilder setContents(int textResId) {
        tvMessageContent.setText(textResId);
        return this;
    }

    public MessageDialogBuilder setContents(CharSequence msg) {
        tvMessageContent.setText(msg);
        return this;
    }

    public MessageDialogBuilder setSubmit(int textResId) {
        btnMessageSubmit.setText(textResId);
        return this;
    }

    public MessageDialogBuilder setSubmit(CharSequence msg) {
        btnMessageSubmit.setText(msg);
        return this;
    }

    public MessageDialogBuilder setCancel(int textResId) {
        btnMessageCancel.setText(textResId);
        return this;
    }

    public MessageDialogBuilder setCancel(CharSequence msg) {
        btnMessageCancel.setText(msg);
        return this;
    }

    public void setOnClickListener(IMessageCallBack callBack) {
        this.callBack = callBack;
    }

    public interface IMessageCallBack {
        void setSubmitListener();

        void setCancelListener();
    }
}
