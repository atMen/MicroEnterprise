package com.tcrj.micro.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tcrj.micro.application.MyApplication;

/**
 * Created by hanzhiwei on 2016/4/14.
 */
public class MyTextViewXH extends TextView {

    public MyTextViewXH(Context context) {
        super(context);
        setFont();
    }

    public MyTextViewXH(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public MyTextViewXH(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }



    private void setFont(){
        this.setTypeface(MyApplication.FZLTXH);
    }


}
