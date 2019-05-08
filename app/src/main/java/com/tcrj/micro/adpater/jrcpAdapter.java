package com.tcrj.micro.adpater;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.bankalInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class jrcpAdapter extends BaseQuickAdapter<bankalInfo.ContentBean, BaseViewHolder> {

    private int mContext;

    public jrcpAdapter(@Nullable List<bankalInfo.ContentBean> data, int type) {
        super(R.layout.jrcp_list_item, data);
        this.mContext = type;
    }

    @Override
    protected void convert(final BaseViewHolder helper, bankalInfo.ContentBean item) {


        helper.setText(R.id.title, mContext == 0? item.getTitle() : item.getName());
        helper.setText(R.id.date, item.getIntroduction());

    }

}
