package com.tcrj.micro.adpater;

/**
 * Created by leict on 2018/11/27.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.tcrj.micro.R;
import com.tcrj.micro.entity.BumenInfo;

import java.util.List;

public class MoreAdapter extends BaseAdapter {

    private Context context;
    private int position = -1;
    private boolean isFirst;
    Holder hold;
    private List<BumenInfo.ComlistBean.DeptlistBean> lists;

    public MoreAdapter(Context context, List<BumenInfo.ComlistBean.DeptlistBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }
    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int arg0, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.item_morelist, null);
            hold = new Holder(view);
            view.setTag(hold);
        } else {
            hold = (Holder) view.getTag();
        }
        hold.txt.setText(lists.get(arg0).getName());
        hold.txt.setTextColor(0xFF666666);

        if(isFirst){
//            if (arg0 == 0) {
//                hold.txt.setTextColor(0xFFFF8C00);
//            }else {
//                hold.txt.setTextColor(0xFF666666);
//            }

        }else {
            if (arg0 == position) {
                hold.txt.setTextColor(0xFFFF8C00);
            }
        }

        return view;
    }

    public void setSelectItem(int position) {
        this.position = position;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    private static class Holder {
        TextView txt;

        public Holder(View view) {
            txt = (TextView) view.findViewById(R.id.moreitem_txt);
        }
    }
}

