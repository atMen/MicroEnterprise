package com.spring.chanba.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.bean.TalentLoanEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 人才贷
 */
public class TalentLoanAdapter extends PagerAdapter {
    private Context mContext;
    private List<TalentLoanEntity> dataList;

    public TalentLoanAdapter(Context context) {
        this.mContext = context;
        this.dataList = new ArrayList<>();
    }

    public void setData(List<TalentLoanEntity> list) {
        this.dataList.clear();
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_talent_viewpager, null);
        TextView tvTalentTitle = (TextView) view.findViewById(R.id.tv_talent_title);
        TextView tvTalentContent1 = (TextView) view.findViewById(R.id.tv_talent_content1);
        TextView tvTalentContent2 = (TextView) view.findViewById(R.id.tv_talent_content2);
        TextView tvTalentContent3 = (TextView) view.findViewById(R.id.tv_talent_content3);
        tvTalentTitle.setText("| " + dataList.get(position).getTitle());
        tvTalentContent1.setText(dataList.get(position).getContent1());
        tvTalentContent2.setText(dataList.get(position).getContent2());
        tvTalentContent3.setText(dataList.get(position).getContent3());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return (float) 0.46;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
