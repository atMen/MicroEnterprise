package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.jzfp.PhototViewActivity;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.entity.fpjlListInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.until.ShowImageUtils;

import java.util.HashMap;
import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class fpdtAdapter extends BaseQuickAdapter<fpjlListInfo.ContentBean, BaseViewHolder> {
    private Context mContext;

    public fpdtAdapter(@Nullable List<fpjlListInfo.ContentBean> data, Context context) {
        super(R.layout.item_fpdt, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final fpjlListInfo.ContentBean item) {

        ImageView imageView = helper.getView(R.id.iv_dtfp);
        helper.setText(R.id.tv_fpdt,item.getUserName());

        helper.setText(R.id.tv_time,item.getAidTime());
        helper.setText(R.id.tv_content,item.getAidRecord());




        if(item.getPicPath().size() > 0){
            ShowImageUtils.showImageView(mContext,
                    ApiConstants.BASEIMAGE+item.getPicPath().get(0),imageView);

        }else {
            Glide.with(mContext).load("").into(imageView);
        }
    }
}
