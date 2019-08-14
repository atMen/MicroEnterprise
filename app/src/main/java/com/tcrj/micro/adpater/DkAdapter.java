package com.tcrj.micro.adpater;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.qyInfo;
import com.tcrj.micro.entity.yqfpInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class DkAdapter extends BaseQuickAdapter<qyInfo.ContentBean, BaseViewHolder>{
    private Context mContext;

    public DkAdapter(@Nullable List<qyInfo.ContentBean> data, Context context) {
        super(R.layout.item_message, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, qyInfo.ContentBean item) {
        TextView name = helper.getView(R.id.qyname);
        TextView time = helper.getView(R.id.qyxydm);
        ImageView ivtrue = helper.getView(R.id.iv_true);
        ImageView ivfalse = helper.getView(R.id.iv_false);
        String mobile = item.getMobile();
        if(!"".equals(mobile)){
            mobile = mobile.replace(mobile.substring(3,7), "****");
        }

        name.setText(item.getCname());
        time.setText("手机: "+mobile);


        if(item.isselect()){
            ivtrue.setVisibility(View.VISIBLE);
            ivfalse.setVisibility(View.GONE);
        }else {
            ivtrue.setVisibility(View.GONE);
            ivfalse.setVisibility(View.VISIBLE);
        }

    }


}
