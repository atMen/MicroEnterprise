package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.qyzpListInfo;
import com.tcrj.micro.entity.zcgsInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class cityAdapter extends BaseQuickAdapter<zcgsInfo, BaseViewHolder> {

    public cityAdapter(@Nullable List<zcgsInfo> data) {
        super(R.layout.item_city, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, zcgsInfo item) {

        helper.setText(R.id.city_name, item.getName());


    }

}
