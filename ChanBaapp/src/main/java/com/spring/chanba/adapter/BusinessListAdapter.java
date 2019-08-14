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
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 工商服务/经典案例
 */
public class BusinessListAdapter extends BaseAdapter {
    private List<LawServiceListEntity.DataBean> itemList;
    private Context mContext;
    private LayoutInflater inflater;

    public BusinessListAdapter(Context context) {
        this.mContext = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<LawServiceListEntity.DataBean> list) {
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
        final LawServiceListEntity.DataBean entity = itemList.get(position);
        if (entity == null)
            return null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_business_content, null);
        }
        TextView tvServiceContent = ViewHolder.get(view, R.id.tv_service_content);
        TextView tvServiceType = ViewHolder.get(view, R.id.tv_service_type);
        TextView tvServiceRelasetime = ViewHolder.get(view, R.id.tv_service_relasetime);
        ImageView imgServicePicture = ViewHolder.get(view, R.id.img_service_picture);
        view.setId(position);
        if (!Utils.isStringEmpty(entity.getPicPath())) {
//            Glide.with(mContext).load(entity.getPicPath()).into(imgServicePicture);
        }
        tvServiceContent.setText(entity.getTitle());
        tvServiceRelasetime.setText(entity.getAddTime());
        tvServiceType.setText(entity.getAuthor());
        return view;
    }
}