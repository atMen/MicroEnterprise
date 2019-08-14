package com.spring.chanba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spring.chanba.R;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 工商服务
 */
public class BusinessServiceAdapter extends BaseAdapter {
    private List<ServiceMenuEntity.DataBean> itemList;
    private Context mContext;
    private LayoutInflater inflater;

    public BusinessServiceAdapter(Context context) {
        this.mContext = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<ServiceMenuEntity.DataBean> list) {
        this.itemList.clear();
        this.itemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (itemList == null) {
            return 0;
        }
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
    public View getView(final int position, View view, ViewGroup parent) {
        final ServiceMenuEntity.DataBean entity = itemList.get(position);
        if (entity == null)
            return null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_business_grid, null);
        }
        ImageView imgBuinessLogo = ViewHolder.get(view, R.id.img_buiness_logo);
        TextView tvBuinessTitle = ViewHolder.get(view, R.id.tv_buiness_title);
        view.setId(position);
        if (!Utils.isStringEmpty(entity.getTitle())) {
            tvBuinessTitle.setText(entity.getTitle());
        }
//        Glide.with(mContext).load(entity.getPicPath()).into(imgBuinessLogo);
        return view;
    }
}
