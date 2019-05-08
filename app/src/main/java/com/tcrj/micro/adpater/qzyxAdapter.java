package com.tcrj.micro.adpater;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.JlInfo;
import com.tcrj.micro.entity.zcgsInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class qzyxAdapter extends BaseQuickAdapter<JlInfo.JobWillsBean, BaseViewHolder> {

    public qzyxAdapter(@Nullable List<JlInfo.JobWillsBean> data) {
        super(R.layout.item_qzyx, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, JlInfo.JobWillsBean item) {

        helper.setText(R.id.tv_qwxz, "期望薪资："+item.getExpectMoney());
        helper.setText(R.id.tv_qwzy, "期望职业："+item.getExpectProfession());
        helper.setText(R.id.tv_qzlx, "求职类型："+item.getJobType());
        helper.setText(R.id.tv_mqzt, "目前状态："+item.getJobStatus());

    }

}
