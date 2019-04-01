package com.tcrj.micro.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tcrj.micro.R;
import com.tcrj.micro.entity.zcgsInfo;

import java.util.List;



/**
 *
 * Created by leict on 2017/10/25.
 */

public class dialogAdapter extends BaseAdapter {
    private List<zcgsInfo> itemList;
    private final Context context;
    private final LayoutInflater inflater;

    public dialogAdapter(Context context, List<zcgsInfo> list) {
        this.context = context;
        if(list != null){
            itemList = list;

        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {


        return itemList.size()-4;
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

        if(itemList == null)
        return null;

        zcgsInfo listinfoBean = itemList.get(id);
        if (listinfoBean == null)
            return null;
        final ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_dialog, null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.sb);


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.name.setText(listinfoBean.getName());


        return view;
    }

    private class ViewHolder {
        TextView name;

    }
}
