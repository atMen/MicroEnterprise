package com.tcrj.micro.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tcrj.micro.application.MyApplication;

/**
 * Created by hanzhiwei on 2016/4/14.
 */
public class MyTextViewZH extends TextView {

    public MyTextViewZH(Context context) {
        super(context);
        setFont();
    }

    public MyTextViewZH(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public MyTextViewZH(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }



    private void setFont(){
        this.setTypeface(MyApplication.FZLTZH);
    }
}
