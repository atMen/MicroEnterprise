package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.tdjlInfo;
import com.tcrj.micro.entity.tsjlInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class tsjlAdapter extends BaseQuickAdapter<tsjlInfo.ContentBean, BaseViewHolder> {

    private Context mContext;

    public tsjlAdapter(@Nullable List<tsjlInfo.ContentBean> data, Context context) {
        super(R.layout.item_tsjl, data);
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, tsjlInfo.ContentBean item) {
        helper.setText(R.id.tv_zw, item.getTitle());

        helper.setText(R.id.time, item.getOptime());
        helper.setText(R.id.content_text, item.getMessage());


    }

}
