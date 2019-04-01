package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.jzfptzInfo;

import java.util.List;




/**
 * desc: .List<jzfptzInfo.ProvinceBean> province
 * author: Will .
 * date: 2017/9/27 .
 */
public class jzfptzAdapter extends BaseQuickAdapter<jzfptzInfo.ProvinceBean, BaseViewHolder> {
    private Context mContext;

    public jzfptzAdapter(@Nullable List<jzfptzInfo.ProvinceBean> data, Context context) {
        super(R.layout.jzfp_tz_item, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, jzfptzInfo.ProvinceBean item) {

        helper.setText(R.id.city, item.getCityName());
        helper.setText(R.id.gzjg, "工作机构："+item.getWorkOrganization());
        helper.setText(R.id.name, "负责人："+item.getPrincipal());
        helper.setText(R.id.phone, "联系电话："+item.getMobile());
        helper.setText(R.id.cyfptsy, "参与扶贫体私营企业数量："+item.getCityName());
        helper.setText(R.id.bfhs, "帮扶户数："+item.getAidPoorNum());
        helper.setText(R.id.jzfp, "捐助扶贫："+item.getAidPoorMoney());
        helper.setText(R.id.zlbf, "智力帮扶："+item.getAidPoorIntelligence());
        helper.setText(R.id.jyfp, "就业扶贫："+item.getAidPoorJob());
        helper.setText(R.id.cybf, "产业帮扶："+item.getAidPoorIndustry());
        helper.setText(R.id.smfp, "商贸扶贫："+item.getAidPoorBussiness());
        helper.setText(R.id.qtfs, "其他方式："+item.getAidPoorOtherType());

//        TextView time = helper.getView(R.id.time);
//        TextView sex = helper.getView(R.id.sex);
//        TextView duty = helper.getView(R.id.duty);
//        ImageView im_icon = helper.getView(R.id.im_icon);

    }


}
