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
 * 成功案例列表
 */
public class SuccessCaseAdapter extends BaseAdapter {
    private Context mContext;
    private List<LawServiceListEntity.DataBean> dataList;
    private LayoutInflater inflater;

    public SuccessCaseAdapter(Context context) {
        this.mContext = context;
        this.dataList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<LawServiceListEntity.DataBean> list) {
        this.dataList.clear();
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final LawServiceListEntity.DataBean entity = dataList.get(position);
        if (entity == null)
            return null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_case_listview, null);
        }
        ImageView imgCasePicture = ViewHolder.get(view, R.id.img_case_picture);
        TextView tvCaseTitle = ViewHolder.get(view, R.id.tv_case_title);
        TextView tvCaseDatetime = ViewHolder.get(view, R.id.tv_case_datetime);
        View viewLine = ViewHolder.get(view, R.id.view_visible);
        if (position == 1) {
            viewLine.setVisibility(View.GONE);
        } else {
            viewLine.setVisibility(View.VISIBLE);
        }
        tvCaseTitle.setText(entity.getTitle());
        tvCaseDatetime.setText(entity.getAddTime());
        if (!Utils.isStringEmpty(entity.getPicPath())) {
            Glide.with(mContext).load(entity.getPicPath()).into(imgCasePicture);
        }
        view.setId(position);
        return view;
    }
}
