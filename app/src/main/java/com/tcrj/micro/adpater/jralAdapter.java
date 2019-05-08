package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.bankalInfo;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.entity.qyzpListInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class jralAdapter extends BaseQuickAdapter<bankalInfo.ContentBean, BaseViewHolder> {

    private int mContext;

    public jralAdapter(@Nullable List<bankalInfo.ContentBean> data, int type) {
        super(R.layout.left_list_item, data);
        this.mContext = type;
    }

    @Override
    protected void convert(final BaseViewHolder helper, bankalInfo.ContentBean item) {


        helper.setText(R.id.title, mContext == 0? item.getTitle() : item.getName());
        helper.setText(R.id.date, item.getIntroduction());

    }

}
