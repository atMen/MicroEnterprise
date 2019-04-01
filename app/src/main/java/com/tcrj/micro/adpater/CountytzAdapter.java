package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

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
public class CountytzAdapter extends BaseQuickAdapter<jzfptzInfo.CountyBean, BaseViewHolder> {
    private Context mContext;

    public CountytzAdapter(@Nullable List<jzfptzInfo.CountyBean> data, Context context) {
        super(R.layout.jzfp_tz_item, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, jzfptzInfo.CountyBean item) {

        helper.getView(R.id.phone).setVisibility(View.GONE);
        helper.getView(R.id.cyfptsy).setVisibility(View.GONE);
        helper.getView(R.id.bfhs).setVisibility(View.GONE);

        helper.setText(R.id.city, item.getEnterpriseName());
        helper.setText(R.id.gzjg, "所在村镇："+item.getTownName());
        helper.setText(R.id.name, "户名："+item.getHomeName());

//        helper.setText(R.id.phone, "联系电话："+item.getMobile());
//        helper.setText(R.id.cyfptsy, "参与扶贫体私营企业数量："+item.getCityName());
//        helper.setText(R.id.bfhs, "帮扶户数："+item.getAidPoorNum());



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
