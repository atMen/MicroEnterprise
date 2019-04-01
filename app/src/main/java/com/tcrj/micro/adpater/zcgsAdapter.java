package com.tcrj.micro.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.micro.R;
import com.tcrj.micro.entity.zcgsInfo;

import java.util.ArrayList;
import java.util.List;




/**
 * 首页菜单适配器
 * Created by leict on 2017/10/25.
 */

public class zcgsAdapter extends BaseAdapter {
    private List<zcgsInfo> itemList;
    private final Context context;
    private final LayoutInflater inflater;

    public zcgsAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemList = new ArrayList<zcgsInfo>();
    }

    public void setData(List<zcgsInfo> list) {
        this.itemList.clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (itemList == null)
            return 0;
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int id, View view, ViewGroup parent) {
        final zcgsInfo entity = (zcgsInfo) itemList.get(id);
        if (entity == null)
            return null;
        final ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_zcgs_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.nameView = (TextView) view.findViewById(R.id.cpname);
            view.setTag(viewHolder);
            view.setId(id);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameView.setText(entity.getName());
        return view;
    }

    private class ViewHolder {
        TextView nameView = null;
    }
}
