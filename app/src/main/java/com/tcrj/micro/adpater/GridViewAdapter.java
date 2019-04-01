package com.tcrj.micro.adpater;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.tcrj.micro.R;
import com.tcrj.micro.entity.CityEntity;

import java.util.List;
import java.util.Map;

public class GridViewAdapter extends BaseAdapter {

	private int kind;
	private int size;
	private List<CityEntity> list;
	private Context context;
	private LayoutInflater inflater;

	public GridViewAdapter(int kind, int size, Context context,
						   List<CityEntity> list) {
		this.kind = kind;
		this.size = size;
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int index, View view, ViewGroup arg2) {
		if (list.size() > 0) {
			CityEntity item = list.get(index);
			if (view == null) {
				view = inflater.inflate(R.layout.grid_item, null);
			}
			view.setBackgroundColor(Color.WHITE);
			TextView textView1 = (TextView) view.findViewById(R.id.grid_name);
			textView1.setText(item.getName());
			return view;
		} else {
			return null;
		}

	}

}
