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
public class JybjAdapter extends BaseQuickAdapter<JlInfo.EducationBackgroundsBean, BaseViewHolder> {

    public JybjAdapter(@Nullable List<JlInfo.EducationBackgroundsBean> data) {
        super(R.layout.item_jybj, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, JlInfo.EducationBackgroundsBean item) {


        helper.setText(R.id.tv_sname, "学校名称："+item.getSchoolName());
        helper.setText(R.id.tv_zy, "专业："+item.getProfessionName());
        helper.setText(R.id.tv_xl, "学历："+item.getEducationBackground());
        helper.setText(R.id.tv_starttime, "入学时间："+item.getStartStudyTime());
        helper.setText(R.id.tv_endtime, "毕业时间："+item.getGraduateTime());


    }

}
