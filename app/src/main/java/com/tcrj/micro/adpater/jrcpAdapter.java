package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.bankalInfo;
import com.tcrj.micro.until.ShowImageUtils;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class jrcpAdapter extends BaseQuickAdapter<bankalInfo.ContentBean, BaseViewHolder> {

    private Context mContext;
    private int type;

    public jrcpAdapter(@Nullable List<bankalInfo.ContentBean> data, int type, Context context) {
        super(R.layout.jrcp_list_item, data);
        this.type = type;
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, bankalInfo.ContentBean item) {

        ImageView imageView = helper.getView(R.id.jrcp_bg);
        ShowImageUtils.showjrcpImageView(mContext,
                ApiConstants.BASEIMAGE+item.getProductimg(),imageView);
        helper.setText(R.id.title, type == 0? item.getTitle() : item.getName());
        helper.setText(R.id.date, item.getIntroduction());

    }

}
