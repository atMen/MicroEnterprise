package com.tcrj.micro.adpater;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.tcrj.micro.R;
import com.tcrj.micro.entity.BumenInfo;

import java.util.List;

public class MainAdapter extends BaseAdapter {

    private Context context;
    private List<BumenInfo.ComlistBean> list;
    private int position = 0;
    private Holder hold;

    public MainAdapter(Context context, List<BumenInfo.ComlistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int arg0, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.item_mainlist, null);
            hold = new Holder(view);
            view.setTag(hold);
        } else {
            hold = (Holder) view.getTag();
        }

//      hold.img.setImageResource(Integer.parseInt(list.get(arg0).getCustId()));
        hold.txt.setText(list.get(arg0).getName());

        if (arg0 == position) {

            hold.layout.setBackgroundColor(context.getResources().getColor(R.color.color_f6));

            hold.img.setVisibility(View.VISIBLE);
        }else {
            hold.img.setVisibility(View.INVISIBLE);
            hold.layout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        return view;
    }

    public void setSelectItem(int position) {
        this.position = position;
    }

    public int getSelectItem() {
        return position;
    }

    private static class Holder {
        LinearLayout layout;
        TextView img;
        TextView txt;

        public Holder(View view) {
            txt = (TextView) view.findViewById(R.id.mainitem_txt);
            img = (TextView) view.findViewById(R.id.mainitem_img);
            layout = (LinearLayout) view.findViewById(R.id.mainitem_layout);
        }
    }
}

