package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.jcInfo;
import com.tcrj.micro.entity.qyzpListInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class grzpAdapter extends BaseQuickAdapter<qyzpListInfo.ContentBean, BaseViewHolder> {

    private Context mContext;

    public grzpAdapter(@Nullable List<qyzpListInfo.ContentBean> data, Context context) {
        super(R.layout.item_grzp, data);
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, qyzpListInfo.ContentBean item) {

        helper.setText(R.id.tv_zw, item.getJobName());
        helper.setText(R.id.xz, item.getSalarRange());
        helper.setText(R.id.xl, item.getEducationBackgroundRequire());
        helper.setText(R.id.gznx, item.getAgeRequire());
        helper.setText(R.id.shi, item.getJobCityName());
        helper.setText(R.id.qu, item.getJobCountyName());

    }

}
