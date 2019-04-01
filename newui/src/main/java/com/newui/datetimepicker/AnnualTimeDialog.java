package com.newui.datetimepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.newui.R;


public class AnnualTimeDialog extends DialogBase {

	private DatePickerCallBack callBack = null;
	private DatePicker datepicker = null;
	private TimePicker timePicker = null;
	private Button btnsubmit = null;
	private Button btncancel = null;
	private int type = 0;


	/*
	* type == 0 年月日
	* type == 1 年月
	* type == 2 年月日 时分秒
	* */
	public AnnualTimeDialog(Context context,int type) {
		super(context);
		this.type = type;
	}

	@Override
	public void setTitleContent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContainer() {
		this.setCanceledOnTouchOutside(true);
		View view = ((LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.annualtime, null);
		addView(view);
		datepicker = (DatePicker) findViewById(R.id.dialog_annual_date);
		timePicker = (TimePicker) findViewById(R.id.dialog_annual_time);
		timePicker.setIs24HourView(true);  
		if(type == 0){
			timePicker.setVisibility(View.GONE);
		}else if(type == 1){
			((ViewGroup)((ViewGroup) datepicker.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
			timePicker.setVisibility(View.GONE);
		}else if(type == 2){
			
		}
		 
		btnsubmit = (Button) findViewById(R.id.btn_annualsubmit);
		btncancel = (Button) findViewById(R.id.btn_annualcancel);
		btnsubmit.setOnClickListener(viewOnClickListen);
		btncancel.setOnClickListener(viewOnClickListen);
	}

	@Override
	public void OnClickListenEvent(View v) {
		if(v.getId() == R.id.btn_annualsubmit){
			callBack.onClickListener(getTime(datepicker,timePicker));
			dismiss();
		}else if(v.getId() == R.id.btn_annualcancel){
			dismiss();
		}
	}

	private String getTime(DatePicker datePicker,TimePicker timePicker) {
		StringBuffer time = new StringBuffer();
		int year = datePicker.getYear();
		int month = datePicker.getMonth() + 1;
		int day = datePicker.getDayOfMonth();
		
		 int hour = timePicker.getCurrentHour();  
         int min = timePicker.getCurrentMinute();  
		
		if(type == 0){
			time.append(year);
			time.append("-");
			time.append(month);
			time.append("-");
			time.append(day);
		}else if(type == 1){
			time.append(year);
			time.append("-");
			time.append(month);
		}else if(type == 2){
			time.append(year);
			time.append("-");
			time.append(month);
			time.append("-");
			time.append(day);
			time.append(" ");
			time.append(hour);
			time.append(":");
			time.append(min);
		}
		return time.toString();
	}

	public void onDatePickerListener(DatePickerCallBack callback) {
		this.callBack = callback;
	}

	public interface DatePickerCallBack {
		public void onClickListener(String time);
	}

}
