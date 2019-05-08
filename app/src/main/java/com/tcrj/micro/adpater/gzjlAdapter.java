package com.tcrj.micro.adpater;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.JlInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class gzjlAdapter extends BaseQuickAdapter<JlInfo.ProjectExperiencesBean, BaseViewHolder> {

    public gzjlAdapter(@Nullable List<JlInfo.ProjectExperiencesBean> data) {
        super(R.layout.item_gzjl, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, JlInfo.ProjectExperiencesBean item) {

        helper.setText(R.id.tv_xmmc, "项目名称："+item.getProjectName());
        helper.setText(R.id.tv_xmms, "项目描述："+item.getProjectDescribe());
        helper.setText(R.id.tv_starttime, "开始时间："+item.getStartTime());
        helper.setText(R.id.tv_endtime, "结束时间："+item.getEndTime());


    }

}
