package com.tcrj.micro.adpater;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.JlInfo;
import com.tcrj.micro.until.DateUtil;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class FgdjAdapter extends BaseQuickAdapter<InfoEntity, BaseViewHolder> {

    public FgdjAdapter(@Nullable List<InfoEntity> data) {
        super(R.layout.left_list_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, InfoEntity item) {

        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.date, "时间:"+ DateUtil.formatToDateString(item.getShowTime()));

    }

}
