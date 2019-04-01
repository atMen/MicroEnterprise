package com.spring.chanba.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.spring.chanba.R;

/**
 * 拍照
 */
public class TakePhotoPopupwindow extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private View view;
    private LinearLayout layoutTakephoto;
    private LinearLayout layoutChosephoto;
    private Button btnTakephotoCancel;
    private TakePhotoCallBack callBack;

    public TakePhotoPopupwindow(Activity context) {
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popup_takephoto, null);
        layoutTakephoto = (LinearLayout) view.findViewById(R.id.layout_takephoto);
        layoutChosephoto = (LinearLayout) view.findViewById(R.id.layout_chosephoto);
        btnTakephotoCancel = (Button) view.findViewById(R.id.btn_takephoto_cancel);
        layoutTakephoto.setOnClickListener(this);
        layoutChosephoto.setOnClickListener(this);
        btnTakephotoCancel.setOnClickListener(this);
        view.setOnClickListener(this);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.showAtLocation(view, Gravity.NO_GRAVITY, view.getMeasuredWidth(), view.getMeasuredHeight());
        btnTakephotoCancel.setOnClickListener(this);
        layoutChosephoto.setOnClickListener(this);
        layoutTakephoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_takephoto_cancel) {
            dismiss();

        } else if (i == R.id.layout_takephoto) {
            if (callBack != null) {
                callBack.setOnTakePhotoListener();
                dismiss();
            }

        } else if (i == R.id.layout_chosephoto) {
            if (callBack != null) {
                callBack.setOnChosePhotoListener();
                dismiss();
            }

        }
    }

    public void setOnItemListener(TakePhotoCallBack callBack) {
        this.callBack = callBack;
    }

    public interface TakePhotoCallBack {

        void setOnTakePhotoListener();

        void setOnChosePhotoListener();
    }
}
