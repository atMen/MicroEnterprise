package com.tcrj.micro.adpater;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.bankInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class jrtzcwAdapter extends BaseQuickAdapter<bankInfo, BaseViewHolder> {

    public jrtzcwAdapter(@Nullable List<bankInfo> data) {
        super(R.layout.cw_list_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, bankInfo item) {

        helper.setText(R.id.title, item.getName());

    }

}
