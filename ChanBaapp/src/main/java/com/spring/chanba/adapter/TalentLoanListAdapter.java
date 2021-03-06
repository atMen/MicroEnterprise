package com.spring.chanba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.bean.TalentLoanListEntity;
import com.spring.chanba.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TalentLoanListAdapter extends BaseAdapter {
    private List<TalentLoanListEntity.DataBean> itemList;
    private Context mContext;
    private LayoutInflater inflater;

    public TalentLoanListAdapter(Context context) {
        this.mContext = context;
        this.itemList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<TalentLoanListEntity.DataBean> list) {
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
        final TalentLoanListEntity.DataBean entity = itemList.get(position);
        if (entity == null)
            return null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_business_content, null);
        }
        TextView tvTalentName = ViewHolder.get(view, R.id.tv_talent_name);
        TextView tvTalentState = ViewHolder.get(view, R.id.tv_talent_state);
        TextView tvTalentTime = ViewHolder.get(view, R.id.tv_talent_time);
        view.setId(position);

        tvTalentName.setText(entity.getGoodsName());
        tvTalentState.setText(entity.getStateName());
        tvTalentTime.setText(entity.getApplyTime());
        return view;
    }
}