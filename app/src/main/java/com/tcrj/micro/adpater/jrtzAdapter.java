package com.tcrj.micro.adpater;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.entity.bankInfo;
import com.tcrj.micro.until.DateUtil;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class jrtzAdapter extends BaseQuickAdapter<bankInfo, BaseViewHolder> {

    public jrtzAdapter(@Nullable List<bankInfo> data) {
        super(R.layout.bank_list_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, bankInfo item) {

        helper.setText(R.id.title, item.getName());

    }

}
