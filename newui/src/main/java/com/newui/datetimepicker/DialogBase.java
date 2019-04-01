package com.newui.datetimepicker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.newui.R;


/**
 * Created by leict on 2015/9/21.
 */
public abstract class DialogBase extends Dialog implements DialogInterface {
	private Window window = null;

	public DialogBase(Context context) {
		super(context, R.style.my_dialog);
		window = getWindow();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitleContent();
		setContainer();
		setWindowParams();
	}

	public void setWindowParams() {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
		window.getWindowManager().getDefaultDisplay().getMetrics(dm);
		p.width = (int) (dm.widthPixels * 0.8); // 宽度设置为屏幕的0.8
		p.width = 800; // 宽度设置为屏幕的0.8
		window.setAttributes(p);
	}

	public void addView(View view) {
		if (window == null) {
			return;
		}
		window.setContentView(view);
	}

	protected void onStop() {
	}

	public View.OnClickListener viewOnClickListen = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			OnClickListenEvent(v);
		}
	};
}
