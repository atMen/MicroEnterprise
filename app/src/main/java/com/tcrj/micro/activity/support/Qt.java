package com.tcrj.micro.activity.support;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.VolleyUtil;
import com.newui.hzwlistview.xlist.XListView;
import com.newui.pageview.BasePageView;
import com.tcrj.micro.JsonParse.JsonParse;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.adpater.SupportListAdapter;
import com.tcrj.micro.constant.Constant;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.SupportEntity;
import com.tcrj.micro.until.DateUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Qt extends BasePageView {

	private Context context;
	private XListView listView;
	public static SupportListAdapter adapter;
	private ArrayList<SupportEntity> list = new ArrayList<SupportEntity>();
	private int pageIndex = 1;
	private boolean isFirst = false;
	private Dialog progressDialog;

	public Qt(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.czzjzc, null);
		addView(view);
		initView();
	}


	@Override
	public void visible() {
		super.visible();
		if(isFirst == false ){
			getDate();
			isFirst = true;
		}

	}

	private void initView() {

		listView = (XListView) findViewById(R.id.listview);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(new XListView.IXListViewListener() {

			@Override
			public void onRefresh() {
				listView.setRefreshTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
				pageIndex = 1;
				getFreshVolley(pageIndex);
			}

			@Override
			public void onLoadMore() {
				pageIndex++;
				getLoadVolley(pageIndex);
			}
		});
		adapter = new SupportListAdapter(context,list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClick());
	}

	public void getDate(){
		showProgressDialog();
		VolleyUtil volleyUtil = new VolleyUtil(context, handler);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pagesize", 10);
		params.put("pageindex", pageIndex);
		params.put("id", "7rmeIn");

		VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

			@Override
			public void onSuccess(JSONObject jsonObject) {
				dismisProgressDialog();
				Log.d("aaa", jsonObject.toString());
				if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
					list.clear();
					ArrayList<SupportEntity> arraylist = JsonParse.getSupportList(jsonObject);
					list.addAll(arraylist);
					if(arraylist.size()<10){
						listView.setPullLoadEnable(false);
					}else{
						listView.setPullLoadEnable(true);
					}
				}else{
					list.clear();
					listView.setPullLoadEnable(false);
				}
				adapter.notifyDataSetChanged();

			}

			@Override
			public void onFailed(String result) {
				dismisProgressDialog();
				handler.sendEmptyMessage(11);
			}
		};
		volleyUtil.getJsonDataFromServer(Constant.findGkInfoList, params, callback2);
	}

	// 刷新
	public void getFreshVolley(int pageNo) {
		showProgressDialog();
		VolleyUtil volleyUtil = new VolleyUtil(context, handler);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pagesize", 10);
		params.put("pageindex", pageIndex);
		params.put("id", "7rmeIn");

		VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

			@Override
			public void onSuccess(JSONObject jsonObject) {
				dismisProgressDialog();
				if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
					ArrayList<SupportEntity> arraylist = JsonParse.getSupportList(jsonObject);
					list.clear();
					list.addAll(arraylist);
					if(arraylist.size()<10){
						listView.setPullLoadEnable(false);
					}else{
						listView.setPullLoadEnable(true);
					}
				}else{
					listView.setPullLoadEnable(false);
					list.clear();

				}
				listView.stopRefresh();
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onFailed(String result) {
				listView.stopRefresh();
				dismisProgressDialog();
				handler.sendEmptyMessage(11);
			}
		};
		volleyUtil.getJsonDataFromServer(Constant.findGkInfoList, params, callback2);

	}

	// 加载
	public void getLoadVolley(int pageNo) {
		showProgressDialog();
		VolleyUtil volleyUtil = new VolleyUtil(context, handler);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pagesize", 10);
		params.put("pageindex", pageIndex);
		params.put("id", "7rmeIn");

		VolleyUtil.VolleyJsonCallback callback2 = new VolleyUtil.VolleyJsonCallback() {

			@Override
			public void onSuccess(JSONObject jsonObject) {
				dismisProgressDialog();
				if(JsonParse.getMsgByKey(jsonObject, "state").equals("1")){
					ArrayList<SupportEntity> arraylist = JsonParse.getSupportList(jsonObject);
					list.addAll(arraylist);
					if(arraylist.size()<10){
						listView.setPullLoadEnable(false);
					}else{
						listView.setPullLoadEnable(true);
					}
				}else{
					listView.setPullLoadEnable(false);
				}
				listView.setSelection(list.size() - 1);
				listView.stopLoadMore();
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onFailed(String result) {
				listView.stopLoadMore();
				dismisProgressDialog();
				handler.sendEmptyMessage(11);
			}
		};
		volleyUtil.getJsonDataFromServer(Constant.findGkInfoList, params, callback2);

	}

	class OnItemClick implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int index,
								long arg3) {
			Intent intent = new Intent();
			intent.putExtra("id",list.get(index-1).getId());
			intent.setClass(context, SupportDetailActivity.class);
			context.startActivity(intent);
		}

	}



	@Override
	public void onSaveInstanceState(Bundle outState) {

	}

	@Override
	public void onDestroy() {

	}

	// 网络加载进度条
	public void showProgressDialog() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
		ProgressBar spaceshipImage = (ProgressBar) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
		tipTextView.setText("正在加载，请稍候...");
		progressDialog = new Dialog(context, R.style.loading_dialog);
		progressDialog.setCancelable(false);
		progressDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		progressDialog.show();
	}

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

	public Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 9:
					dismisProgressDialog();
					Toast.makeText(context, "服务器异常，获取数据失败",
							Toast.LENGTH_LONG).show();
					break;
				case 10: // 没有网络连接
					dismisProgressDialog();
					Toast.makeText(context, "当前没有网络连接", Toast.LENGTH_LONG)
							.show();
					break;
				case 11: // 获取数据失败
					dismisProgressDialog();
					Toast.makeText(context, "服务器异常，获取数据失败",
							Toast.LENGTH_LONG).show();
					break;

				default:
					break;
			}
		}
	};
}
